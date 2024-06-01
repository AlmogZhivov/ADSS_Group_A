package com.Superlee.HR.Backend.Business;

import com.Superlee.HR.Backend.DataAccess.RolesDTO;

import java.util.HashMap;
import java.util.Map;

class Roles {
    public final Map<Integer, Integer> DEFAULT_SHIFT_ROLES = new HashMap<>() {{
        put(1, 1); // Always require one manager
    }};

    private static Roles instance;
    private Map<Integer, String> roles = new HashMap<>();

    private Roles() {
        loadRoles();
    }

    public static Roles getInstance() {
        if (instance == null)
            instance = new Roles();
        return instance;
    }

    public String getName(int value) {
        return roles.get(value);
    }

    public int getId(String name) {
        for (Map.Entry<Integer, String> entry : roles.entrySet())
            if (entry.getValue().equals(name))
                return entry.getKey();
        return -1;
    }

    public void loadRoles() {
        roles = RolesDTO.loadRoles();
    }
}
