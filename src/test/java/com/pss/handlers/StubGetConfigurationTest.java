package com.pss.handlers;

import java.lang.Object;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StubGetConfigurationTest {
    @Test
    void getSetting() {
        HashMap<String, Object> settings = new HashMap<>();
        settings.put("gravity", 9.81d);
        settings.put("weight", 10.0d);
        StubGetConfiguration configHandler = new StubGetConfiguration(settings);

        assertEquals(9.81d, configHandler.getSetting("gravity"));
        assertEquals(10.0d, configHandler.getSetting("weight"));
        assertNull(configHandler.getSetting("unknown"));
    }

    // TODO add more tests
}
