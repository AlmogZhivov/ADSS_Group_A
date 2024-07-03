package com.Superlee.HR.Backend.DataAccess;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShiftDTO extends DTO {
    private int id;
    private LocalDateTime startTime;
    private String branch;
    private LocalDateTime endTime;
    private Map<String, Integer> requiredRoles;
    private List<String> availableWorkers;
    private List<String> assignedWorkers;
    private Map<String, Integer> workerRoles;

    public ShiftDTO() {
        this.controller = new ShiftController(this).setTestMode(testMode);
        requiredRoles = new HashMap<>();
        availableWorkers = new ArrayList<>();
        assignedWorkers = new ArrayList<>();
        workerRoles = new HashMap<>();
    }

    public ShiftDTO(int id, String branch,
                    LocalDateTime startTime, LocalDateTime endTime,
                    Map<String, Integer> requiredRoles, List<String> availableWorkers,
                    List<String> assignedWorkers, Map<String, Integer> workerRoles) {
        this();
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requiredRoles = requiredRoles;
        this.availableWorkers = availableWorkers;
        this.assignedWorkers = assignedWorkers;
        this.workerRoles = workerRoles;
        this.branch = branch;
    }

    public ShiftDTO(ShiftDTO other) {
        this();
        this.id = other.getId();
        this.startTime = other.getStartTime();
        this.endTime = other.getEndTime();
        this.requiredRoles = other.getRequiredRoles();
        this.availableWorkers = other.getAvailableWorkers();
        this.assignedWorkers = other.getAssignedWorkers();
        this.workerRoles = other.getWorkerRoles();
        this.branch = other.getBranch();
    }

    @Override
    public List<ShiftDTO> loadAll() {
        return ((ShiftController) controller).loadAll();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Map<String, Integer> getRequiredRoles() {
        return requiredRoles;
    }

    public void setRequiredRoles(Map<String, Integer> requiredRoles) {
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

    public void setWorkerRoles(Map<String, Integer> workerRoles) {
        this.workerRoles = workerRoles;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String name) {
        this.branch = name;
    }

//    public static List<ShiftDTO> getShifts() {
//        ShiftDTO shift1 = new ShiftDTO(1, "Branch1", LocalDateTime.of(2024, 7, 1, 8, 0, 0), LocalDateTime.of(2024, 7, 1, 12, 0, 0), null, null, null, null);
//        ShiftDTO shift2 = new ShiftDTO(2, "Branch2", LocalDateTime.of(2024, 7, 1, 12, 0, 0), LocalDateTime.of(2024, 7, 1, 16, 0, 0), null, null, null, null);
//        ShiftDTO shift3 = new ShiftDTO(3, "Branch1", LocalDateTime.of(2024, 7, 1, 16, 0, 0), LocalDateTime.of(2024, 7, 1, 20, 0, 0), null, null, null, null);
//        ShiftDTO shift4 = new ShiftDTO(4, "Branch3", LocalDateTime.of(2024, 7, 2, 8, 0, 0), LocalDateTime.of(2024, 7, 2, 12, 0, 0), null, null, null, null);
//
//        return List.of(shift1, shift2, shift3, shift4);
//    } // TODO remove
}
