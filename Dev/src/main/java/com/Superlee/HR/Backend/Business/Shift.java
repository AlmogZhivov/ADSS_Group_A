package com.Superlee.HR.Backend.Business;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

class Shift {
    private final String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<Integer, Integer> requiredRoles;
    private List<String> availableWorkers;
    private List<String> assignedWorkers;
    private final Map<String, Integer> workerRoles;

    public Shift(String id) {
        this.id = id;
        this.requiredRoles = new HashMap<>();
        this.availableWorkers = new ArrayList<>();
        this.assignedWorkers = new ArrayList<>();
        this.workerRoles = new HashMap<>();
    }

    public void addAvailableWorker(String worker) {
        if (!availableWorkers.contains(worker))
            availableWorkers.add(worker);
    }

    public void removeAvailableWorker(String worker) {
        availableWorkers.remove(worker);
    }

    public void assignWorker(String worker, Integer role) {
        if (availableWorkers.contains(worker) && !assignedWorkers.contains(worker) && requiredRoles.containsKey(role)) {
            assignedWorkers.add(worker);
            workerRoles.put(worker, role);
        }
    }

    public void removeAssignedWorker(String worker) {
        assignedWorkers.remove(worker);
        workerRoles.remove(worker);
    }

    public boolean isFullyStaffed() {
        Map<Integer, Integer> roleCount = new HashMap<>();
        for (Integer role : workerRoles.values())
            roleCount.put(role, roleCount.getOrDefault(role, 0) + 1);

        for (Map.Entry<Integer, Integer> entry : requiredRoles.entrySet())
            if (!roleCount.containsKey(entry.getKey()) || roleCount.get(entry.getKey()) < entry.getValue())
                return false;
        return true;
    }


    // Getters and setters

    public String getId() {
        return id;
    }

    public Map<Integer, Integer> getRequiredRoles() {
        return requiredRoles;
    }

    public void setRequiredRoles(Map<Integer, Integer> requiredRoles) {
        this.requiredRoles = requiredRoles;
    }

    public List<String> getAvailableWorkers() {
        return availableWorkers;
    }

    public void setAvailableWorkers(List<String> availableWorkers) {
        this.availableWorkers = availableWorkers;
    }

    public List<String> getAssignedWorkers() {
        return assignedWorkers;
    }

    public void setAssignedWorkers(List<String> assignedWorkers) {
        this.assignedWorkers = assignedWorkers;
    }

    public Map<String, Integer> getWorkerRoles() {
        return workerRoles;
    }
}

