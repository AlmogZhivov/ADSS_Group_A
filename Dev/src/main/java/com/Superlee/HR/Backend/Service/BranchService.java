package com.Superlee.HR.Backend.Service;

import com.Superlee.HR.Backend.Business.BranchFacade;
import com.google.gson.Gson;

public class BranchService {
    private static BranchService instance;
    private final BranchFacade bf = BranchFacade.getInstance();
    private Gson gson;

    private BranchService() {
    }

    public static BranchService getInstance() {
        if (instance == null)
            instance = new BranchService();

        return instance;
    }

    public String addBranch(String name, String address, String manager) {
        gson = new Gson();
        try {
            bf.addBranch(name, address, manager);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String getBranch(String name) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(bf.getBranch(name)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String updateManager(String name, String manager) {
        gson = new Gson();
        try {
            bf.updateManager(name, manager);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String getAllBranches() {
        gson = new Gson();
        try {
            return gson.toJson(new Response(bf.getAllBranches()));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String getBranchByName(String name) {
        gson = new Gson();
        try {
            return gson.toJson(new Response(bf.getBranchByName(name)));
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String addNewBranch(String name, String address, String managerId) {
        gson = new Gson();
        try {
            bf.addBranch(name, address, managerId);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String updateBranchManager(String branchName, String managerId) {
        gson = new Gson();
        try {
            bf.updateManager(branchName, managerId);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public String updateWorkerMainBranch(String id, String branch) {
        gson = new Gson();
        try {
            bf.updateWorkerMainBranch(id, branch);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            Response res = new Response(ex.getMessage());
            return gson.toJson(new Response(res));
        }
    }

    public boolean loadData() {
        try {
            bf.loadData();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
