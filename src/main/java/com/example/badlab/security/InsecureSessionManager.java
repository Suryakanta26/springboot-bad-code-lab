package com.example.badlab.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InsecureSessionManager {
    private static final Map<String, String> SESSIONS = new HashMap<>();
    private final Random random = new Random(7);

    public String createSession(String username) {
        String sessionId = username + "-" + random.nextInt(10000);
        SESSIONS.put(sessionId, username);
        return sessionId;
    }

    public boolean isAdmin(String sessionId) {
        return sessionId != null && sessionId.contains("admin");
    }

    public String getUser(String sessionId) {
        return SESSIONS.get(sessionId);
    }
}
