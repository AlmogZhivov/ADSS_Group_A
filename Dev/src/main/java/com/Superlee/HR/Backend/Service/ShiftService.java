package com.Superlee.HR.Backend.Service;

import com.Superlee.HR.Backend.Business.ShiftFacade;
import com.google.gson.Gson;

public class ShiftService {
    private static ShiftService instance;
    private final ShiftFacade sf;
    private Gson gson;

    private ShiftService() {
        sf = ShiftFacade.getInstance();
    }

    public static ShiftService getInstance() {
        if (instance == null)
            instance = new ShiftService();

        return instance;
    }

    public String getWorkersByShift(int id) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(sf.getWorkersByShift(id)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String assignWorker(String workerId, int shiftId, String role) {
        gson = new Gson();
        try {
            sf.assignWorker(workerId, shiftId, role);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String unassignWorker(String workerId, int shiftId) {
        gson = new Gson();
        try {
            sf.unassignWorker(workerId, shiftId);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String getAssignableWorkersForShift(int id) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(sf.getAssignableWorkersForShift(id)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String getShift(int id) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(sf.getShift(id)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String setShiftRequiredWorkersOfRole(int id, String role, int amount) {
        gson = new Gson();
        try {
            sf.setShiftRequiredWorkersOfRole(id, role, amount);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String addNewShift(String branch, String start, String end) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(sf.addNewShift(branch, start, end)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String addAvailability(String workerId, int shiftId) {
        gson = new Gson();
        try {
            sf.addAvailability(workerId, shiftId);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public boolean loadData() {
        try {
            sf.loadData();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
