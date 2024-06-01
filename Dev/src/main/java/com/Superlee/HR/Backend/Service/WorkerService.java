package com.Superlee.HR.Backend.Service;

import com.Superlee.HR.Backend.Business.WorkerFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorkerService {
    private static WorkerService workerService;
    private final WorkerFacade wf;
    private Gson gson;

    private WorkerService() {
        wf = WorkerFacade.getInstance();
        gson = new GsonBuilder().serializeNulls().create();
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

    public String getWorkersByName(String firstname, String surname) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.getWorkersByName(firstname, surname)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String getWorkerById(String id) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.getWorkerById(id)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String addNewWorker(String id, String firstname, String surname) {
        gson = new Gson();
        try {
            wf.addNewWorker(id, firstname, surname);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public boolean loadData() {
        try {
            return wf.loadData();
        } catch (Exception ignored) {
            return false;
        }
    }

    public String updateWorkerEmail(String id, String email) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.updateWorkerEmail(id, email)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }

    }

    public String updateWorkerPhone(String id, String phone) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.updateWorkerPhone(id, phone)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String updateWorkerPassword(String id, String password) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.updateWorkerPassword(id, password)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String updateWorkerBankDetails(String id, String bankDetails) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.updateWorkerBankDetails(id, bankDetails)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String updateWorkerContractDetails(String id, String contractDetails) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.updateWorkerContractDetails(id, contractDetails)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String updateWorkerSalary(String id, int salary) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(wf.updateWorkerSalary(id, salary)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String login(String id, String password) {
        try {
            return gson.toJson(new Response(wf.login(id, password)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }
}
