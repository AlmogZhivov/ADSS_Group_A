package com.Superlee.HR.Backend.Business;

import java.time.LocalDateTime;

public class Util {
    public static boolean isNullOrEmpty(String... strings) {
        for (String s : strings)
            if (s == null || s.isEmpty())
                return true;

        return false;
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        // TODO check if this is correct
    }

    public static boolean isValidDateTime(String... strings) {
        for (String s : strings)
            try {
                LocalDateTime.parse(s);
            } catch (Exception e) {
                return false;
            }

        return true;
    }
}
