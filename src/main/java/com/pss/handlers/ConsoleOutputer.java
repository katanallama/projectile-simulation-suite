package com.pss.handlers;
import javax.vecmath.Vector3d;

import com.pss.SimulatorState;
import com.pss.enums.State;
import com.pss.interfaces.IOutputResults;

public class ConsoleOutputer implements IOutputResults {
    private static final int OUTPUT_FREQUENCY = 1000; // Output each 1000th result

    public void outputResults(Vector3d[] results, double timeStep) {
        System.out.printf("%10s %10s %10s %10s\n", "Time Step", "X", "Y", "Z");
        for (int i = 0; i < results.length; i++) {
            if (i % OUTPUT_FREQUENCY == 0) {
                Vector3d vector = results[i];
                double time = i * timeStep;
                System.out.printf("%10.2f %10.1f %10.1f %10.1f\n", time, vector.x, vector.y, vector.z);
            }
        }
        // Print the timestep at which the projectile lands
        Vector3d last = results[results.length-1];
        System.out.printf("%10.2f %10.1f %10.1f %10.1f\n", results.length*timeStep, last.x, last.y, last.z);
        SimulatorState.setCurrentState(State.PRINT_RESULTS_TO_CONSOLE);
    }
}
