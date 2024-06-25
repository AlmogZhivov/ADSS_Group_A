package com.Superlee.HR.Backend.DataAccess;

import java.util.HashMap;
import java.util.Map;

public class RolesDTO {
    private int value;
    private String name;

    public RolesDTO(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Map<String, Integer> loadRoles() {
        Map<String, Integer> roles = new HashMap<>();
        roles.put("HRManager", 0);
        roles.put("Manager", 1);
        roles.put("Cashier", 2);
        roles.put("Cleaner", 3);
        roles.put("Storekeeper", 4);
        roles.put("Security", 5);
        roles.put("Driver", 6);
        return roles;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
