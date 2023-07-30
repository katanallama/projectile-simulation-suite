package com.pss.handlers;

import javax.vecmath.Vector3d;
import java.util.HashMap;

import com.pss.enums.Settings;
import com.pss.interfaces.IGetConfiguration;

public class BaseGetConfiguration implements IGetConfiguration {
    protected static HashMap<String, Object> getDefaultSettings() {
        HashMap<String, Object> defaultSettings = new HashMap<String, Object>();

        for (Settings setting : Settings.values()) {
            defaultSettings.put(setting.getName(), setting.getDefault());
        }

        return defaultSettings;
    }

    public BaseGetConfiguration() {
        _settings = getDefaultSettings();
    }

    public BaseGetConfiguration(HashMap<String, Object> settings) {
        _settings = getDefaultSettings();

        overrideSettings(settings);
    }

    protected void overrideSettings(HashMap<String, Object> settings) {
        for (String key : settings.keySet()) {
            overrideSetting(key, settings.get(key));
        }
    }

    protected void overrideSetting(String key, Object value) {
        if (value instanceof String || value instanceof Vector3d) {
            System.out.printf("   %-20s   %-40s\n", key, value);
        }
        if (!(value instanceof String || value instanceof Vector3d)) {
            System.out.printf("   %-20s   %-40.3f\n", key, value);
        }

        if (value == null) {
            return;
        }

        if (_settings.get(key) != null) {
            _settings.remove(key);
            _settings.put(key, value);
        } else {
            _settings.put(key, value);
        }
    }

    private HashMap<String, Object> _settings;

    @SuppressWarnings("unchecked") // we do a validation of the cast ourselves
    public <T> T getSetting(Settings setting) {
        T value = (T) _settings.get(setting.getName());

        if (!Settings.validateValue(value, setting)) {
            throw new RuntimeException("We attempted to get " + setting.getName() + " as a type "
                    + value.getClass().getName() + " but it should be " + setting.getType().getRawType().getName());
        }

        return value;
    }
}
