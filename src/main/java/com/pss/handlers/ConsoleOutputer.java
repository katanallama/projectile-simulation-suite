package com.pss.handlers;
import javax.vecmath.Vector3d;

import com.pss.SimulatorState;
import com.pss.enums.State;
import com.pss.interfaces.IOutputResults;

public class ConsoleOutputer implements IOutputResults {
    private static final int OUTPUT_FREQUENCY = 100; // Output each 100th result

    public void outputResults(Vector3d[] results, double timeStep) {
        System.out.println("\nRUNNING SIMULATION...");
        System.out.printf("%10s %10s %10s %10s\n", "Time Step", "X", "Y", "Z");

        for (int i = 0; i < results.length; i++) {
            if (i % OUTPUT_FREQUENCY == 0) {
                Vector3d vector = results[i];
                double time = i * timeStep;
                System.out.printf("%10.2f %10.1f %10.1f %10.1f\n", time, vector.x, vector.y, vector.z);
            }
        }

        Vector3d last = results[results.length-1];
        System.out.printf("%10.2f %10.1f %10.1f %10.1f\n", results.length*timeStep, last.x, last.y, last.z);
        System.out.println("\nSIMULATION COMPLETE, use ctrl+c to quit");
        SimulatorState.setCurrentState(State.PRINT_RESULTS_TO_CONSOLE);
    }
}
