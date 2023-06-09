package com.pss.handlers;
import com.pss.interfaces.IOutputResults;
import javax.vecmath.Vector3d;

public class ConsoleOutputer implements IOutputResults{
    public void outputResults(Vector3d[] results) {
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i].toString());
        }
    }
}
