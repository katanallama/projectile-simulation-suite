package com.pss.enums;

import javax.naming.directory.InvalidAttributesException;
import javax.vecmath.Vector3d;

public enum Settings {
    Gravity("gravity", DataTypes.Double, 9.81d),
    Weight("weight", DataTypes.Double, 14.0d),
    InitialForce("initialForce", DataTypes.Double, 100.d),
    ContinualForce("continualForce", DataTypes.Double, 20.0d),
    InitialDirection("initialDirection", DataTypes.Vector, new Vector3d(10, 10, 10)),
    FluidRho("fluidRho", DataTypes.Double, 1.204d),
    ProjectileArea("projectileArea", DataTypes.Double, 0.1d),
    DragCoefficent("dragCoefficent", DataTypes.Double, 0.04d),
    MaxStep("simStep", DataTypes.Double, 1d),
    TimeStep("timeStep", DataTypes.Double, 0.1d),
    InputFile("inFile", DataTypes.String, "simulatorSettings.json"),
    OutputFile("outFile", DataTypes.String, "image.png");

    private String name;
    private DataTypes type;
    private Object defaultValue;

    private Settings(String name, DataTypes type) {
        this.name = name;
        this.type = type;
        this.defaultValue = null;
    }

    private Settings(String name, DataTypes type, Object defaultValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public DataTypes getType() {
        return type;
    }

    public Object getDefault() {
        return defaultValue;
    }

    public static double parseDouble(String in) throws InvalidAttributesException {
        String cleanedIn = in.replaceAll("[^-.0-9]", "");

        if (cleanedIn == "") {
            throw new InvalidAttributesException(in + " is not formatted correctly. Please enter a number");
        }

        return Double.parseDouble(cleanedIn);
    }

    public static Vector3d parseVector(String vector) throws InvalidAttributesException {
        String[] parts = vector.split(",");
        Vector3d parsedVector;

        if (parts.length != 3) {
            throw new InvalidAttributesException(
                    vector + " is not formatted correctly. Please use (x,y,z) as a format");
        }
        try {
            parsedVector = new Vector3d(parseDouble(parts[0]), parseDouble(parts[1]), parseDouble(parts[2]));
        } catch (InvalidAttributesException ex) {
            throw new InvalidAttributesException(
                    vector + " is not formatted correctly. Please use (x,y,z) as a format");
        }

        return parsedVector;
    }

    public static String parseString(String in) {
        return in.trim(); // Simple sanitization by removing leading and trailing white spaces.
    }

    public static <T> boolean validateValue(T value, Settings setting) {
        switch (setting.getType()) {
            case Double:
                return (value instanceof Double);
            case Vector:
                return (value instanceof Vector3d);
            case String:
                return (value instanceof String);
            default:
                return false;
        }
    }
}
