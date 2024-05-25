package com.Superlee.HR.Backend.Business;

import com.Superlee.HR.Backend.DataAccess.ShiftDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        if (shifts.containsKey(id))
            return shifts.get(id).getAssignedWorkers().stream().map(workerFacade::getWorkerById).collect(Collectors.toList());
        return null;
    }

    public boolean assignWorker(String workerId, int shiftId, String role) {
        if (!shifts.containsKey(shiftId) || workerFacade.getWorkerById(workerId) == null || roles.getId(role) == -1)
            return false;

        if (!workerFacade.assignWorker(workerId, shiftId, role))
            return false;

        if (!shifts.get(shiftId).assignWorker(workerId, roles.getId(role)))
            if (!workerFacade.unassignWorker(workerId, shiftId))
                throw new IllegalStateException("Unexpected error, reverting changes failed");
            else
                return false;

        return true;
    }

    public boolean unassignWorker(String workerId, int shiftId) {
        if (!shifts.containsKey(shiftId) || workerFacade.getWorkerById(workerId) == null)
            return false;

        if (!shifts.get(shiftId).removeAssignedWorker(workerId))
            return false;

        if (!workerFacade.unassignWorker(workerId, shiftId))
            if (!shifts.get(shiftId).removeAssignedWorker(workerId))
                throw new IllegalStateException("Unexpected error, reverting changes failed");
            else
                return false;

        return true;
    }

    public List<WorkerToSend> getAssignableWorkersForShift(int id) {
        if (!shifts.containsKey(id))
            return null;
        return workerFacade.getAllWorkers().stream().filter((worker) -> shifts.get(id).getAvailableWorkers().contains(worker.id())).collect(Collectors.toList());
    }

    public ShiftToSend getShift(int id) {
        if (shifts.containsKey(id))
            return convertToShiftToSend(shifts.get(id));
        return null;
    }

    public boolean loadData() {
        shifts = ShiftDTO.getShifts().stream().map(ShiftFacade::ShiftDTOtoShift).collect(Collectors.toMap(Shift::getId, w -> w));
        nextId = shifts.values().stream().mapToInt(Shift::getId).max().orElse(0) + 1;
        return true;
    }

    private static Shift ShiftDTOtoShift(ShiftDTO sDTO) {
        return new Shift(
                sDTO.getId(),
                LocalDateTime.parse(sDTO.getStartTime().toString()),
                LocalDateTime.parse(sDTO.getEndTime().toString())
        );
    }

    public boolean setShiftRequiredWorkersOfRole(int id, String role, int amount) {
        if (!shifts.containsKey(id) || roles.getId(role) == -1 || amount < 0)
            return false;
        shifts.get(id).getRequiredRoles().put(roles.getId(role), amount);
        return true;
    }

    public int addNewShift(String start, String end) {
        if (start == null || end == null)
            return -1;
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime))
            return -1;
        Shift s = new Shift(nextId, startTime, endTime);
        shifts.put(s.getId(), s);
        ++nextId;
        return s.getId();
    }

    /**
     * Used for testing purposes only, no need to have a service for this for now
     */
    public List<ShiftToSend> getAllShifts() {
        return shifts.values().stream().map(ShiftFacade::convertToShiftToSend).collect(Collectors.toList());
    }

    private static ShiftToSend convertToShiftToSend(Shift shift) {
        return new ShiftToSend(shift.getId(), shift.getStartTime().toString(), shift.getEndTime().toString());
    }

    /**
     * Reset the workers map.
     * DEBUGGING PURPOSES ONLY
     * DO NOT USE IN PRODUCTION
     */
    public void reset(int safetyCode) {
        if (safetyCode != 0xC0FFEE)
            System.exit(-1);
        shifts.clear();
    }

    public boolean addAvailability(String workerId, int shiftId) {
        if (!shifts.containsKey(shiftId) || workerFacade.getWorkerById(workerId) == null)
            return false;

        if (!shifts.get(shiftId).addAvailableWorker(workerId))
            return false;

        if (!workerFacade.addAvailability(workerId, shiftId))
            if (!shifts.get(shiftId).removeAvailableWorker(workerId))
                throw new IllegalStateException("Unexpected error, reverting changes failed");
            else
                return false;

        return true;
    }
}
