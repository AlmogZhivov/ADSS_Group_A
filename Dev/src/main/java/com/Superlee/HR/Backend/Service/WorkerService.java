package com.Superlee.HR.Backend.Service;

import com.Superlee.HR.Backend.Business.WorkerFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorkerService {
    private static WorkerService workerService;
    private final WorkerFacade wf;
    private final Gson gson;

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
        try {
            return gson.toJson(new Response(wf.getAllWorkers()));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String getWorkersByRole(String role) {
        try {
            return gson.toJson(new Response(wf.getWorkersByRole(role)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String getWorkersByName(String firstname, String surname) {
        try {
            return gson.toJson(new Response(wf.getWorkersByName(firstname, surname)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String getWorkerById(String id) {
        try {
            return gson.toJson(new Response(wf.getWorkerById(id)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String addNewWorker(String id, String firstname, String surname) {
        try {
            wf.addNewWorker(id, firstname, surname);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
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
        try {
            return gson.toJson(new Response(wf.updateWorkerEmail(id, email)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }

    }

    public String updateWorkerPhone(String id, String phone) {
        try {
            return gson.toJson(new Response(wf.updateWorkerPhone(id, phone)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String updateWorkerPassword(String id, String password) {
        try {
            return gson.toJson(new Response(wf.updateWorkerPassword(id, password)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String updateWorkerBankDetails(String id, String bankDetails) {
        try {
            return gson.toJson(new Response(wf.updateWorkerBankDetails(id, bankDetails)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String updateWorkerContractDetails(String id, String contractDetails) {
        try {
            return gson.toJson(new Response(wf.updateWorkerContractDetails(id, contractDetails)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String updateWorkerSalary(String id, int salary) {
        try {
            return gson.toJson(new Response(wf.updateWorkerSalary(id, salary)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
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
