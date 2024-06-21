package com.Superlee.HR.Backend.Business;

import com.Superlee.HR.Backend.DataAccess.ShiftDTO;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.*;
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

        workerFacade.requireHRManagerOrThrow();

        if (!shifts.containsKey(id))
            throw new NoSuchElementException("Shift not found");

        return shifts.get(id).getAssignedWorkers().stream().map(workerFacade::getWorkerById).collect(Collectors.toList());
    }

    public boolean assignWorker(String workerId, int shiftId, String role) {
        if (shiftId < 0 || Util.isNullOrEmpty(workerId, role))
            throw new IllegalArgumentException("Illegal argument");

        workerFacade.requireHRManagerOrThrow();

        if (!shifts.containsKey(shiftId))
            throw new NoSuchElementException("Shift not found");

        if (workerFacade.getWorkerById(workerId) == null)
            throw new NoSuchElementException("Worker not found");

        if (roles.getId(role) == null)
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

        workerFacade.requireHRManagerOrThrow();

        if (!shifts.containsKey(shiftId))
            throw new NoSuchElementException("Shift not found");

        if (workerFacade.getWorkerById(workerId) == null)
            throw new NoSuchElementException("Worker not found");

        if (!shifts.get(shiftId).getAssignedWorkers().contains(workerId))
            throw new IllegalStateException("Worker is not assigned to this shift");

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

        workerFacade.requireHRManagerOrThrow();

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

    public boolean setShiftRequiredWorkersOfRole(int id, String role, int amount) {
        if (id < 0 || amount < 0 || Util.isNullOrEmpty(role))
            throw new IllegalArgumentException("Illegal argument");

        workerFacade.requireHRManagerOrThrow();

        if (!shifts.containsKey(id))
            throw new NoSuchElementException("Shift not found");

        if (roles.getId(role) == null)
            throw new NoSuchElementException("Role not found");

        shifts.get(id).getRequiredRoles().put(role, amount);
        return true;
    }

    public int addNewShift(String start, String end, String branch) {
        // TODO check if shift is on a saturday, check if branch exists
        if (Util.isNullOrEmpty(start, end, branch))
            throw new IllegalArgumentException("Illegal argument");

        workerFacade.requireHRManagerOrThrow();

        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime))
            throw new DateTimeException("Invalid time range");

        Shift s = new Shift(nextId++, startTime, endTime, branch);
        shifts.put(s.getId(), s);
        return s.getId();
    }

    public boolean addAvailability(String workerId, int shiftId) {
        if (shiftId < 0 || Util.isNullOrEmpty(workerId))
            throw new IllegalArgumentException("Illegal argument");

        workerFacade.requireLoginOrThrow(workerId);

        if (!shifts.containsKey(shiftId))
            throw new NoSuchElementException("Shift not found");

        if (workerFacade.getWorkerById(workerId) == null)
            throw new NoSuchElementException("Worker not found");

        if (!shifts.get(shiftId).addAvailableWorker(workerId))
            throw new IllegalStateException("Worker is already available for this shift");

        if (!workerFacade.addAvailability(workerId, shiftId))
            if (!shifts.get(shiftId).removeAvailableWorker(workerId))
                throw new IllegalStateException("Unexpected error, reverting changes failed");
            else
                throw new IllegalStateException("Unexpected error");

        return true;
    }

    public boolean removeAvailability(String workerId, int shiftId) {
        if (shiftId < 0 || Util.isNullOrEmpty(workerId))
            throw new IllegalArgumentException("Illegal argument");

        workerFacade.requireLoginOrThrow(workerId);

        if (!shifts.containsKey(shiftId))
            throw new NoSuchElementException("Shift not found");

        if (workerFacade.getWorkerById(workerId) == null)
            throw new NoSuchElementException("Worker not found");

        if (!shifts.get(shiftId).getAvailableWorkers().contains(workerId))
            throw new IllegalStateException("You are not available for this shift");

        if (shifts.get(shiftId).getAssignedWorkers().contains(workerId))
            throw new IllegalStateException("You are already assigned to this shift, contact your manager to unassign you");

        if (!workerFacade.removeAvailability(workerId, shiftId) || !shifts.get(shiftId).removeAvailableWorker(workerId))
            throw new IllegalStateException("Unexpected error");

        return true;
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

    public List<ShiftToSend> getWorkerHistory(String id) {
        Util.throwIfNullOrEmpty(id);

        // Maybe check if the worker is logged in?

        return shifts
                .values()
                .stream()
                .filter(s -> s.getAssignedWorkers().contains(id))
                .map(ShiftFacade::convertToShiftToSend)
                .collect(Collectors.toList());
    }

    public List<ShiftToSend> getWorkerHistory(String id, String from, String to) {
        List<ShiftToSend> list = getWorkerHistory(id);

        // Maybe check if the worker is logged in?

        if (!Util.isValidDateTime(from, to))
            throw new IllegalArgumentException("Invalid date format");

        return list.stream().
                filter(s -> LocalDateTime.parse(s.endTime()).isBefore(LocalDateTime.parse(to))
                            && LocalDateTime.parse(s.startTime()).isAfter(LocalDateTime.parse(from)))
                .toList();
    }

    public List<ShiftToSend> getShiftsByBranchAndDate(String from, String to, String branchName) {
        Util.throwIfNullOrEmpty(from, to, branchName);
        if (!Util.isValidDateTime(from, to))
            throw new IllegalArgumentException("Illegal argument");

        return shifts
                .values()
                .stream()
                .filter(s -> s.getEndTime().isBefore(LocalDateTime.parse(to))
                             && s.getStartTime().isAfter(LocalDateTime.parse(from))
                             && s.getBranch().equals(branchName))
                .map(ShiftFacade::convertToShiftToSend)
                .collect(Collectors.toList());
    }
}
