package com.Superlee.HR.Backend.Service;

import com.Superlee.HR.Backend.Business.WorkerFacade;

import com.google.gson.Gson;

public class WorkerService {
    private static WorkerService workerService;
    private final WorkerFacade wf;
    private Gson gson;

    private WorkerService() {
        wf = WorkerFacade.getInstance();
    }

    public static WorkerService getInstance() {
        if (workerService == null)
            workerService = new WorkerService();

        return workerService;
    }

    public String getAllWorkers() {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.getAllWorkers()));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String getWorkersByRole(String role) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.getWorkersByRole(role)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String getWorkersByName(String name) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.getWorkersByName(name)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String getWorkersById(int id) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.getWorkersById(id)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String addNewWorker(String name) {
        gson = new Gson();
        try {
            wf.addNewWorker(name);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String loadData() {
        gson = new Gson();
        try {
            wf.loadData();
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }
}
