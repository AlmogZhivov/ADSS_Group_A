package com.Superlee.HR.Backend.DataAccess;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ShiftDTO {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<Integer, Integer> requiredRoles;
    private List<String> availableWorkers;
    private List<String> assignedWorkers;
    private Map<String, Integer> workerRoles;

    public ShiftDTO(int id, LocalDateTime startTime, LocalDateTime endTime, Map<Integer, Integer> requiredRoles, List<String> availableWorkers, List<String> assignedWorkers, Map<String, Integer> workerRoles) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requiredRoles = requiredRoles;
        this.availableWorkers = availableWorkers;
        this.assignedWorkers = assignedWorkers;
        this.workerRoles = workerRoles;
    }

    public boolean insertShift() {
        return true;
    }

    public boolean updateShift() {
        return true;
    }

    public boolean deleteShift() {
        return true;
    }

    public static List<ShiftDTO> getShifts() {
        ShiftDTO shift1 = new ShiftDTO(1, LocalDateTime.of(2024, 7, 1, 8, 0, 0), LocalDateTime.of(2024, 7, 1, 12, 0, 0), null, null, null, null);
        ShiftDTO shift2 = new ShiftDTO(2, LocalDateTime.of(2024, 7, 1, 12, 0, 0), LocalDateTime.of(2024, 7, 1, 16, 0, 0), null, null, null, null);
        ShiftDTO shift3 = new ShiftDTO(3, LocalDateTime.of(2024, 7, 1, 16, 0, 0), LocalDateTime.of(2024, 7, 1, 20, 0, 0), null, null, null, null);
        ShiftDTO shift4 = new ShiftDTO(4, LocalDateTime.of(2024, 7, 2, 8, 0, 0), LocalDateTime.of(2024, 7, 2, 12, 0, 0), null, null, null, null);

        return List.of(shift1, shift2, shift3, shift4);
    }
}
