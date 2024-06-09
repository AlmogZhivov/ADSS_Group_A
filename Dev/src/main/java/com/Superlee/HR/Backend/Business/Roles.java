package com.Superlee.HR.Backend.Business;

import com.Superlee.HR.Backend.DataAccess.RolesDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Roles {
    public final Map<String, Integer> DEFAULT_SHIFT_ROLES = new HashMap<>() {{
        put("Manager", 1); // Always require one manager
    }};

    private static Roles instance;
    private Map<String, Integer> roles = new HashMap<>();

    private Roles() {
        loadRoles();
    }

    static Roles getInstance() {
        if (instance == null)
            instance = new Roles();
        return instance;
    }

    String getName(int value) {
        for (var entry : roles.entrySet())
            if (entry.getValue().equals(value))
                return entry.getKey();
        return null;
    }

    Integer getId(String name) {
        return roles.get(name);
    }

    void loadRoles() {
        roles = RolesDTO.loadRoles();
    }

    void addNewRole(String name) {
        if (roles.containsKey(name))
            throw new IllegalArgumentException("Role already exists");

        roles.put(name, roles.size() + 1);
    }

    Set<String> getAllRoleNames() {
        return roles.keySet();
    }
}
