package org.bot.utilities;

import java.util.HashMap;
import java.util.Map;

public class Responses {
    private static final Responses instance = new Responses();
    private static final Map<String, String> RESPONSES = new HashMap<>() {{
        put("mofumofu", "fuwafuwa!");
        put("fuwafuwa", "mofumofu!");
    }};

    private Responses() {}

    public static Responses getInstance() {
        return instance;
    }

    public Map<String, String> getResponses() {
        return RESPONSES;
    }
}
