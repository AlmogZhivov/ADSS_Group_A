package com.Superlee.HR.Backend.Service;

public class HRService {
    private static HRService instance;
    private final ShiftService ss;
    private final WorkerService ws;

    private HRService() {
        ss = ShiftService.getInstance();
        ws = WorkerService.getInstance();
//        ss.loadData();
//        ws.loadData();
        // TODO make sure that loadData is called for both services
    }

    public static HRService getInstance() {
        if (instance == null)
            instance = new HRService();

        return instance;
    }

    public String call(String command) {
        // TODO gsonify the response
        String[] parts = command.split("\\s+");
        int i = 0;
        String action = parts[i++];
        switch (action) {
            case "getAllWorkers":
                return ws.getAllWorkers();

            case "getWorkersByRole":
                return parts.length != 2 ? ws.getWorkersByRole(parts[i]) : "Invalid number of args";

            case "getWorkersByName":
                return parts.length != 2 ? ws.getWorkersByName(parts[i]) : "Invalid number of args";


            case "getWorkersById":
                return parts.length != 2 ? tryParseInt(parts[i]) ? ws.getWorkersById(Integer.parseInt(parts[i])) : "Invalid ID" : "Invalid number of args";


            case "addNewWorker":
                return parts.length == 2 ? ws.addNewWorker(parts[i]) : "Invalid number of args";


            case "getWorkersByShift":
                return parts.length == 2 ? tryParseInt(parts[i]) ? ss.getWorkersByShift(Integer.parseInt(parts[i])) : "Invalid ID" : "Invalid number of args";

            case "assignWorker": {
                if (parts.length != 4)
                    return "Invalid number of args";

                if (!tryParseInt(parts[1]) || !tryParseInt(parts[2]))
                    return "Invalid ID";

                int workerId = Integer.parseInt(parts[1]);
                int shiftId = Integer.parseInt(parts[2]);
                String Role = parts[3];
                return ss.assignWorker(workerId, shiftId, Role);
            }

            case "unassignWorker":
                if (parts.length != 3)
                    return "Invalid number of args";

                if (!tryParseInt(parts[1]) || !tryParseInt(parts[2]))
                    return "Invalid ID";

                int workerId = Integer.parseInt(parts[1]);
                int shiftId = Integer.parseInt(parts[2]);

                return ss.unassignWorker(workerId, shiftId);

            case "getAssignableWorkersByShift":
                return parts.length == 2 ? tryParseInt(parts[i]) ? ss.getAssignableWorkersForShift(Integer.parseInt(parts[i])) : "Invalid ID" : "Invalid number of args";

            case "getShift":
                return parts.length == 2 ? tryParseInt(parts[i]) ? ss.getShift(Integer.parseInt(parts[i])) : "Invalid ID" : "Invalid number of args";

            case "setShiftRequiredWorkersOfRole":

                if (parts.length != 3)
                    return "Invalid number of args";
                String role = parts[i++];
                return tryParseInt(parts[i]) ? ss.setShiftRequiredWorkersOfRole(role, Integer.parseInt(parts[i])) : "Invalid ID";
            // Add other commands as needed
            default:
                return "Unknown command: " + action;
        }
    }

    private boolean tryParseInt(String val) {
        int assignRole;
        try {
            assignRole = Integer.parseInt(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAllWorkers() {
        return ws.getAllWorkers();
    }

    public String getWorkersByRole(String role) {
        return ws.getWorkersByRole(role);
    }

    public String getWorkersByName(String name) {
        return ws.getWorkersByName(name);
    }

    public String getWorkersById(int id) {
        return ws.getWorkersById(id);
    }

    public String addNewWorker(String name) {
        return ws.addNewWorker(name);
    }

    public String getWorkersByShift(int id) {
        return ss.getWorkersByShift(id);
    }

    public String assignWorker(int workerId, int shiftId, String role) {
        return ss.assignWorker(workerId, shiftId, role);
    }

    public String unassignWorker(int workerId, int shiftId) {
        return ss.unassignWorker(workerId, shiftId);
    }

    public String getAssignableWorkersForShift(int id) {
        return ss.getAssignableWorkersForShift(id);
    }

    public String getShift(int id) {
        return ss.getShift(id);
    }

    public String setShiftRequiredWorkersOfRole(String role, int id) {
        return ss.setShiftRequiredWorkersOfRole(role, id);
    }
}
