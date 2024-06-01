package com.Superlee.HR.Backend.DataAccess;

import java.util.ArrayList;
import java.util.List;

public class BranchDTO {
    private String name;
    private String address;
    private String manager;

    public BranchDTO(String name, String address, String manager) {
        this.name = name;
        this.address = address;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getManager() {
        return manager;
    }

    public static List<BranchDTO> getBranches() {
        List<BranchDTO> branches = new ArrayList<>();
        branches.add(new BranchDTO("Branch1", "Address1", "Manager1"));
        branches.add(new BranchDTO("Branch2", "Address2", "Manager2"));
        branches.add(new BranchDTO("Branch3", "Address3", "Manager3"));
        return branches;
    }
}
