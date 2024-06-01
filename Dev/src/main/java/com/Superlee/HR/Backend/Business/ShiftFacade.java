package com.Superlee.HR.Backend.Business;

import com.Superlee.HR.Backend.DataAccess.ShiftDTO;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ShiftFacade {
    private static ShiftFacade instance;
    private static final WorkerFacade workerFacade = WorkerFacade.getInstance();
    private final Roles roles = Roles.getInstance();

    private int nextId = 0;

    private Map<Integer, Shift> shifts;

    private ShiftFacade() {
        shifts = new HashMap<>();
    }

    public static ShiftFacade getInstance() {
        if (instance == null)
            instance = new ShiftFacade();

        return instance;
    }

    public List<WorkerToSend> getWorkersByShift(int id) {
        if (id < 0)
            throw new IllegalArgumentException("Illegal argument");

        if (!shifts.containsKey(id))
            throw new NoSuchElementException("Shift not found");

        return shifts.get(id).getAssignedWorkers().stream().map(workerFacade::getWorkerById).collect(Collectors.toList());
    }

    public boolean assignWorker(String workerId, int shiftId, String role) {
        if (shiftId < 0 || Util.isNullOrEmpty(workerId, role))
            throw new IllegalArgumentException("Illegal argument");

        if (!shifts.containsKey(shiftId))
            throw new NoSuchElementException("Shift not found");

        if (workerFacade.getWorkerById(workerId) == null)
            throw new NoSuchElementException("Worker not found");

        if (roles.getId(role) == -1)
            throw new NoSuchElementException("Role not found");

        if (!workerFacade.assignWorker(workerId, shiftId, role))
            throw new IllegalStateException("Unexpected error");

        if (!shifts.get(shiftId).assignWorker(workerId, roles.getId(role)))
            if (!workerFacade.unassignWorker(workerId, shiftId))
                throw new IllegalStateException("Unexpected error, reverting changes failed");
            else
                throw new IllegalStateException("Unexpected error");

        return true;
    }

    public boolean unassignWorker(String workerId, int shiftId) {
        if (shiftId < 0 || Util.isNullOrEmpty(workerId))
            throw new IllegalArgumentException("Illegal argument");

        if (!shifts.containsKey(shiftId))
            throw new NoSuchElementException("Shift not found");

        if (workerFacade.getWorkerById(workerId) == null)
            throw new NoSuchElementException("Worker not found");

        if (!shifts.get(shiftId).removeAssignedWorker(workerId))
            throw new IllegalStateException("Unexpected error");

        if (!workerFacade.unassignWorker(workerId, shiftId))
            if (!shifts.get(shiftId).removeAssignedWorker(workerId))
                throw new IllegalStateException("Unexpected error, reverting changes failed");
            else
                throw new IllegalStateException("Unexpected error");

        return true;
    }

    public List<WorkerToSend> getAssignableWorkersForShift(int id) {
        if (id < 0)
            throw new IllegalArgumentException("Illegal argument");

        if (!shifts.containsKey(id))
            throw new NoSuchElementException("Shift not found");

        return workerFacade
                .getAllWorkers()
                .stream()
                .filter((worker) -> shifts.get(id).getAvailableWorkers().contains(worker.id()))
                .collect(Collectors.toList());
    }

    public ShiftToSend getShift(int id) {
        if (id < 0)
            throw new IllegalArgumentException("Illegal argument");

        if (!shifts.containsKey(id))
            throw new NoSuchElementException("Shift not found");

        return convertToShiftToSend(shifts.get(id));
    }

    public boolean loadData() {
        shifts = ShiftDTO
                .getShifts()
                .stream()
                .map(ShiftFacade::ShiftDTOtoShift)
                .collect(Collectors.toMap(Shift::getId, w -> w));
        nextId = shifts.values().stream().mapToInt(Shift::getId).max().orElse(0) + 1;
        return true;
    }

    private static Shift ShiftDTOtoShift(ShiftDTO sDTO) {
        return new Shift(
                sDTO.getId(),
                LocalDateTime.parse(sDTO.getStartTime().toString()),
                LocalDateTime.parse(sDTO.getEndTime().toString()),
                sDTO.getBranch()
        );
    }

    public boolean setShiftRequiredWorkersOfRole(int id, String role, int amount) {
        if (id < 0 || amount < 0 || Util.isNullOrEmpty(role))
            throw new IllegalArgumentException("Illegal argument");

        if (!shifts.containsKey(id))
            throw new NoSuchElementException("Shift not found");

        if (roles.getId(role) == -1)
            throw new NoSuchElementException("Role not found");

        shifts.get(id).getRequiredRoles().put(roles.getId(role), amount);
        return true;
    }

    public int addNewShift(String branch, String start, String end) {
        // TODO check if shift is on a saturday, check if branch exists
        if (Util.isNullOrEmpty(branch, start, end))
            throw new IllegalArgumentException("Illegal argument");

        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime))
            throw new DateTimeException("Invalid time range");

        Shift s = new Shift(nextId++, startTime, endTime, branch);
        shifts.put(s.getId(), s);
        return s.getId();
    }

    /**
     * Used for testing purposes only, no need to have a service for this for now
     */
    public List<ShiftToSend> getAllShifts() {
        return shifts.values().stream().map(ShiftFacade::convertToShiftToSend).collect(Collectors.toList());
    }

    private static ShiftToSend convertToShiftToSend(Shift shift) {
        return new ShiftToSend(shift.getId(), shift.getStartTime().toString(), shift.getEndTime().toString(), shift.getBranch());
    }

    /**
     * Reset the shifts map.
     * DEBUGGING PURPOSES ONLY
     * DO NOT USE IN PRODUCTION
     */
    public void reset(int safetyCode) {
        if (safetyCode != 0xC0FFEE)
            System.exit(-1);
        shifts.clear();
    }

    public boolean addAvailability(String workerId, int shiftId) {
        if (shiftId < 0 || Util.isNullOrEmpty(workerId))
            throw new IllegalArgumentException("Illegal argument");

        if (!shifts.containsKey(shiftId))
            throw new NoSuchElementException("Shift not found");

        if (workerFacade.getWorkerById(workerId) == null)
            throw new NoSuchElementException("Worker not found");

        if (!shifts.get(shiftId).addAvailableWorker(workerId))
            throw new IllegalStateException("Unexpected error");

        if (!workerFacade.addAvailability(workerId, shiftId))
            if (!shifts.get(shiftId).removeAvailableWorker(workerId))
                throw new IllegalStateException("Unexpected error, reverting changes failed");
            else
                throw new IllegalStateException("Unexpected error");

        return true;
    }
}
