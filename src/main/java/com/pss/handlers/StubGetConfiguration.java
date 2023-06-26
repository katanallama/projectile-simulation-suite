package com.pss.handlers;
import com.pss.interfaces.*;

import java.lang.Object;
import java.util.HashMap;
import javax.vecmath.Vector3d;

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
        _settings.put("initialForce", 100.0d);
        _settings.put("initialDirection", new Vector3d(10, 10, 10));
        _settings.put("continualForce", 20.0d);
    }
    
    private HashMap<String, Object> _settings;
    
    public <T> T getSetting(String settingName) {
        return (T)_settings.get(settingName);        
    };
}
