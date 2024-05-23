package com.Superlee.HR.Backend.Business;

public enum Role {
    MANAGER(0), CASHIER(1), CLEANER(2);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Role getRole(int value) {
        for (Role role : Role.values())
            if (role.getValue() == value)
                return role;

        return null;
    }

    public static String getRoleName(int value) {
        Role role = getRole(value);
        if (role != null)
            return role.name();

        return null;
    }

    public static String getRoleName(Role role) {
        return role.name();
    }

    public static Role getRole(String name) {
        for (Role role : Role.values())
            if (role.name().equals(name))
                return role;

        return null;
    }

    public static int getRoleValue(String name) {
        Role role = getRole(name);
        if (role != null)
            return role.getValue();

        return -1;
    }
}
