package com.Superlee.HR.Backend.DataAccess;

import java.util.List;

public class RolesDTO extends DTO {
    private int value;
    private String name;

    public RolesDTO() {
        this.controller = new RolesController();
    }

    public RolesDTO(int value, String name) {
        this();
        this.value = value;
        this.name = name;
    }

    public RolesDTO(RolesDTO other) {
        this();
        this.value = other.getValue();
        this.name = other.getName();
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

    @Override
    public List<RolesDTO> loadAll() {
        return List.of(); // TODO
    }
    //    public static Map<String, Integer> loadRoles() { // TODO remove
//        Map<String, Integer> roles = new HashMap<>();
//        roles.put("HRManager", 0);
//        roles.put("Manager", 1);
//        roles.put("Cashier", 2);
//        roles.put("Cleaner", 3);
//        roles.put("Storekeeper", 4);
//        roles.put("Security", 5);
//        roles.put("Driver", 6);
//        return roles;
//    }
}
