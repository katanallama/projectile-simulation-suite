package com.pss.handlers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pss.enums.Settings;
import com.pss.enums.State;
import com.pss.SimulatorState;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import java.lang.reflect.Type;
import java.util.HashMap;

import javax.naming.directory.InvalidAttributesException;

public class FileGetConfiguration extends BaseGetConfiguration {
    private static final String FILE_PATH = "simulatorSettings.json";

    public static HashMap<String, Object> parseJsonFile(String filePath) {
        Gson gson = new Gson();

        HashMap<String, Object> jsonFileSettings = new HashMap<String, Object>();

        try (FileReader reader = new FileReader(filePath)) {
            SimulatorState.setCurrentState(State.READ_FILE);

            Type type = new TypeToken<HashMap<String, String>>(){}.getType();

            HashMap<String, String> rawData  = gson.fromJson(reader, type);

            SimulatorState.setCurrentState(State.PARSE_CONFIG);

            for (Settings setting : Settings.values()) {
                if (rawData.containsKey(setting.getName())) {
                    Object value = null;

                    try {
                        switch (setting.getType()) {
                            case Double:
                                value = Settings.parseDouble(rawData.get(setting.getName()));
                                break;
                            case Vector:
                                value = Settings.parseVector(rawData.get(setting.getName()));
                                break;
                            default:
                                continue;
                        }
                    } catch (InvalidAttributesException ex ){
                        System.out.println(ex.getMessage());
                        continue;
                    }

                    jsonFileSettings.put(setting.getName(), value);
                }
            }
            SimulatorState.setCurrentState(State.STORE_CONFIG);

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

        return jsonFileSettings;
    }

    public FileGetConfiguration() {
        initializeSettings();
    }

    public FileGetConfiguration(HashMap<String, Object> settings) {
        initializeSettings();
        System.out.print(settings);

        overrideSettings(settings);
    }

    private void initializeSettings() {
        overrideSettings(parseJsonFile(FILE_PATH));
    }
}
