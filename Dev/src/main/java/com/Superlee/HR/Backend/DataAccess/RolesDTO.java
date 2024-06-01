package com.Superlee.HR.Backend.DataAccess;

import java.util.Map;

public class RolesDTO {
    private int value;
    private String name;

    public RolesDTO(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Map<Integer, String> loadRoles() {
        return Map.of(
                0, "HRManager",
                1, "Manager",
                2, "Cashier",
                3, "Cleaner",
                4, "Storekeeper",
                5, "Security"
        );
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
