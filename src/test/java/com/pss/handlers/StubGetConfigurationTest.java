package com.pss.handlers;

import java.lang.Object;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import com.pss.enums.Settings;

import static org.junit.jupiter.api.Assertions.*;

class StubGetConfigurationTest {
    @Test
    void getSetting() {
        HashMap<String, Object> settings = new HashMap<>();
        settings.put("gravity", 9.81d);
        settings.put("weight", 10.0d);
        BaseGetConfiguration configHandler = new BaseGetConfiguration(settings);

        assertEquals(9.81d, configHandler.getSetting(Settings.Gravity));
    }

    // TODO add more tests
}
