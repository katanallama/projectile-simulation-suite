package com.pss.handlers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import javax.naming.directory.InvalidAttributesException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pss.SimulatorState;
import com.pss.enums.Settings;
import com.pss.enums.State;

public class FileGetConfiguration extends BaseGetConfiguration {

    // private static String filePath;
    private String filePath;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_ORANGE = "\u001B[38;5;214m";

    public static HashMap<String, Object> parseJsonFile(String filePath) {
        Gson gson = new Gson();
        HashMap<String, Object> jsonFileSettings = new HashMap<String, Object>();

        try {
            jsonFileSettings = processJsonFile(filePath, gson);

            System.out.println(
                    ANSI_ORANGE + "\nConfig file found at " + filePath + ", overriding settings...\n"
                            + ANSI_RESET);
        } catch (FileNotFoundException e) {
            System.out.println(
                    ANSI_ORANGE + "\nConfig file not found at " + filePath + ", using default settings...\n"
                            + ANSI_RESET);
            for (Settings setting : Settings.values()) {
                jsonFileSettings.put(setting.getName(), setting.getDefault());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonFileSettings;
    }

    private static HashMap<String, Object> processJsonFile(String filePath, Gson gson) throws IOException {
        HashMap<String, Object> jsonFileSettings = new HashMap<String, Object>();

        try (FileReader reader = new FileReader(filePath)) {
            // SimulatorState.setCurrentState(State.READ_FILE);
            // setCurrentState(State.READ_FILE);
            Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            HashMap<String, String> rawData = gson.fromJson(reader, type);
            // SimulatorState.setCurrentState(State.PARSE_CONFIG);
            // setCurrentState(State.PARSE_CONFIG);
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
                    } catch (InvalidAttributesException ex) {
                        System.out.println(ex.getMessage());
                        continue;
                    }
                    jsonFileSettings.put(setting.getName(), value);
                }
            }
            // SimulatorState.setCurrentState(State.STORE_CONFIG);
        }
        return jsonFileSettings;
    }

    public FileGetConfiguration(String file) {
        this.filePath = "config/" + file + ".json";
        initializeSettings();
    }

    public FileGetConfiguration(HashMap<String, Object> settings) {
        initializeSettings();
        System.out.print(settings);

        overrideSettings(settings);
    }

    private void initializeSettings() {
        overrideSettings(parseJsonFile(filePath));
    }

}
