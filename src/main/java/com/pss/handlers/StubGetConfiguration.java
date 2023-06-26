package com.pss.handlers;
import com.pss.interfaces.*;

import java.lang.Object;
import java.util.HashMap;

public class StubGetConfiguration implements IGetConfiguration {
    public StubGetConfiguration() {
        _settings = new HashMap<String, Object>();
        
        initializeSettings();
    }
    
    public StubGetConfiguration(HashMap<String, Object> settings) {
        _settings = settings;

        initializeSettings();
    }

    private void initializeSettings() {
        _settings.put("gravity", 9.81d);
        _settings.put("weight", 10.0d);
        _settings.put("initial force", 100.0d);
        _settings.put("continual force", 20.0d);
    }
    
    private HashMap<String, Object> _settings;
    
    public <T> T getSetting(String settingName) {
        return (T)_settings.get(settingName);        
    };
}
