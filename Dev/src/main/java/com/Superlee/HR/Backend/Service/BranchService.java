package com.Superlee.HR.Backend.Service;

import com.Superlee.HR.Backend.Business.BranchFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BranchService {
    private static BranchService instance;
    private final BranchFacade bf;
    private final Gson gson;

    private BranchService() {
        bf = BranchFacade.getInstance();
        gson = new GsonBuilder().serializeNulls().create();
    }

    public static BranchService getInstance() {
        if (instance == null)
            instance = new BranchService();

        return instance;
    }

    public String addBranch(String name, String address, String manager) {
        try {
            bf.addBranch(name, address, manager);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String getBranch(String name) {
        try {
            return gson.toJson(new Response(bf.getBranch(name)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String updateManager(String name, String manager) {
        try {
            bf.updateManager(name, manager);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String getAllBranches() {
        try {
            return gson.toJson(new Response(bf.getAllBranches()));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String getBranchByName(String name) {
        try {
            return gson.toJson(new Response(bf.getBranchByName(name)));
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String addNewBranch(String name, String address, String managerId) {
        try {
            bf.addBranch(name, address, managerId);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String updateBranchManager(String branchName, String managerId) {
        try {
            bf.updateManager(branchName, managerId);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
        }
    }

    public String updateWorkerMainBranch(String id, String branch) {
        try {
            bf.updateWorkerMainBranch(id, branch);
            return gson.toJson(new Response());
        } catch (Exception ex) {
            return gson.toJson(new Response(ex.getMessage()));
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
