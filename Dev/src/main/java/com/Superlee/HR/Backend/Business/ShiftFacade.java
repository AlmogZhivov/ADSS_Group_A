package com.Superlee.HR.Backend.Business;

import java.util.List;

public class ShiftFacade {

    public List<WorkerToSend> getWorkersByShift(int id) {
        return null;
    }

    public boolean assignWorker(int workerId, int shiftId, String role) {
        return true;
    }

    public boolean unassignWorker(int workerId, int shiftId) {
        return true;
    }

    public List<WorkerToSend> getAssignableWorkersForShift(int id) {
        return null;
    }

    public ShiftToSend getShift(int id) {
        return null;
    }

    public boolean loadData() {
        return true;
    }

    public boolean setShiftRequiredWorkersOfRole(String role, int id) {
        return true;
    }
}
