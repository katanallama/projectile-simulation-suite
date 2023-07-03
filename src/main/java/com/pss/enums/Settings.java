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
    DragCoefficent("dragCoefficent", DataTypes.Double, 0.04d);

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
            throw new InvalidAttributesException(vector + " is not formatted correctly. Please use (x,y,z) as a format");
        }
        try {
            parsedVector = new Vector3d(parseDouble(parts[0]),parseDouble(parts[1]), parseDouble(parts[2]));
        } catch (InvalidAttributesException ex) {
            throw new InvalidAttributesException(vector + " is not formatted correctly. Please use (x,y,z) as a format");
        }

        return parsedVector;
    }

    public static <T> boolean validateValue(T value, Settings setting) {
        // We could simplify this with the Java record pattern feature, but since we'll only have a handful of these I think it's good enough
        switch (setting.getType()) {
            case Double:
                return (value instanceof Double);
            case Vector:
                return (value instanceof Vector3d);
            default:
                return false;
        }
    }
}
