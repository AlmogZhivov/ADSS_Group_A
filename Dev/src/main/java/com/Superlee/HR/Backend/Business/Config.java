package com.Superlee.HR.Backend.Business;

import java.util.Map;
import java.util.HashMap;

public class Config {
    final static Map<Role, Integer> default_shift_roles = new HashMap<>() {{
        put(Role.MANAGER, 1);
    }};

    // TODO load from a config file
}
