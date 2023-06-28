package com.pss.handlers;

import com.pss.interfaces.IOutputResults;

import javax.vecmath.Vector3d;

public class ConsoleOutputer implements IOutputResults {
    public void outputResults(Vector3d[] results) {
        for (Vector3d vector : results) {
            System.out.println("position: \t" + formatVector(vector));
        }
    }

    private String formatVector(Vector3d vector) {
        return "(" + formatDouble(vector.x) + ",\t" + formatDouble(vector.y) + ",\t" + formatDouble(vector.z) + ")";
    }

    private String formatDouble(double value) {
        return String.format("%.1f", value);
    }
}
