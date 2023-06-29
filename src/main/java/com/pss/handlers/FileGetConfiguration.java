package com.pss.handlers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pss.interfaces.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import java.lang.reflect.Type;
import java.util.HashMap;

public class FileGetConfiguration implements IGetConfiguration {

    private static final String FILE_PATH = "simulatorSettings.json";

    public static HashMap<String, Object> parseJsonFile(String filePath) {
        Gson gson = new Gson();
        HashMap<String, Object> data = new HashMap<>();

        try (FileReader reader = new FileReader(filePath)) {
            // Define the type of the HashMap using TypeToken
            Type type = new TypeToken<HashMap<String, Object>>(){}.getType();

            // Parse the JSON file into a HashMap
            data = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            File settingsFile = new File(filePath);
            try {
                settingsFile.createNewFile();
            } catch (Exception fileError) {
                fileError.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public FileGetConfiguration() {
        _settings = new HashMap<String, Object>();
        
        initializeSettings();
    }
    
    public FileGetConfiguration(HashMap<String, Object> settings) {
        _settings = settings;

        initializeSettings();
    }

    private void initializeSettings() {
        _settings.put("gravity", 9.81d);
        _settings.put("weight", 10.0d);

        HashMap<String, Object> fileSettings = parseJsonFile(FILE_PATH);
        if (fileSettings != null && fileSettings.size() > 0) {
            _settings.putAll(fileSettings);
        }
    }
    
    private HashMap<String, Object> _settings;
    
    public <T> T getSetting(String settingName) {
        return (T)_settings.get(settingName);        
    };
}
