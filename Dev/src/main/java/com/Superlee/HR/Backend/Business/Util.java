package com.Superlee.HR.Backend.Business;

public class Util {
    public static boolean isNullOrEmpty(String... strings) {
        for (String s : strings) {
            if (s == null || s.isEmpty())
                return true;
        }
        return false;
    }

    public static boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        // TODO check if this is correct
    }
}
