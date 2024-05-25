package com.Superlee.HR.Backend.Service;

public class HRService {
    private static HRService instance;
    private final ShiftService ss;
    private final WorkerService ws;

    private HRService() {
        ss = ShiftService.getInstance();
        ws = WorkerService.getInstance();
        ss.loadData();
        ws.loadData();
    }

    public static HRService getInstance() {
        if (instance == null)
            instance = new HRService();

        return instance;
    }

    /**
     * Get a list of all workers
     *
     * @return a list of all workers
     */
    public String getAllWorkers() {
        return ws.getAllWorkers();
    }

    /**
     * Get a list of all workers with the specified role
     *
     * @param role the role to filter by
     * @return a list of all workers with the specified role
     */
    public String getWorkersByRole(String role) {
        return ws.getWorkersByRole(role);
    }

    /**
     * Get all workers with the specified name
     *
     * @param firstname the first name of the worker
     * @param surname   the surname of the worker
     * @return all workers with the specified name
     */
    public String getWorkersByName(String firstname, String surname) {
        return ws.getWorkersByName(firstname, surname);
    }

    /**
     * Get a worker with the specified id
     *
     * @param id the id of the worker
     * @return the worker with the specified id
     */
    public String getWorkerById(String id) {
        return ws.getWorkerById(id);
    }

    /**
     * Add a new worker to the system
     *
     * @param id        the id of the worker
     * @param firstname the first name of the worker
     * @param surname   the surname of the worker
     * @return an empty response if successful
     */
    public String addNewWorker(String id, String firstname, String surname) {
        return ws.addNewWorker(id, firstname, surname);
    }

    /**
     * Get a list of all workers assigned to the specified shift
     *
     * @param id the id of the shift
     * @return a list of all workers assigned to the specified shift
     */
    public String getWorkersByShift(int id) {
        return ss.getWorkersByShift(id);
    }

    /**
     * Assign a worker to a shift
     *
     * @param workerId the id of the worker
     * @param shiftId  the id of the shift
     * @param role     the role of the worker
     * @return an empty response if successful
     */
    public String assignWorker(String workerId, int shiftId, String role) {
        return ss.assignWorker(workerId, shiftId, role);
    }

    /**
     * Unassign a worker from a shift
     *
     * @param workerId the id of the worker
     * @param shiftId  the id of the shift
     * @return an empty response if successful
     */
    public String unassignWorker(String workerId, int shiftId) {
        return ss.unassignWorker(workerId, shiftId);
    }

    /**
     * Get all workers assignable to the specified shift
     *
     * @param id the id of the shift
     * @return all workers assignable to the specified shift
     */
    public String getAssignableWorkersForShift(int id) {
        return ss.getAssignableWorkersForShift(id);
    }

    /**
     * Get a shift with the specified id
     *
     * @param id the id of the shift
     * @return the shift with the specified id
     */
    public String getShift(int id) {
        return ss.getShift(id);
    }

    /**
     * Set the amount of workers with the specified role needed for a shift
     *
     * @param id     the id of the shift
     * @param role   the role of the worker
     * @param amount the amount of workers needed
     * @return an empty response if successful
     */
    public String setShiftRequiredWorkersOfRole(int id, String role, int amount) {
        return ss.setShiftRequiredWorkersOfRole(id, role, amount);
    }

    /**
     * Add a new shift to the system
     *
     * @param start the start time of the shift
     * @param end   the end time of the shift
     * @return the id of the new shift
     */
    public String addNewShift(String start, String end) {
        return ss.addNewShift(start, end);
    }

    /**
     * Add an availability for a worker to a shift
     *
     * @param workerId the id of the worker
     * @param shiftId  the id of the shift
     * @return an empty response if successful
     */
    public String addAvailability(String workerId, int shiftId) {
        return ss.addAvailability(workerId, shiftId);
    }
}
