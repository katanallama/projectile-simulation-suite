package com.pss;

import com.pss.factories.*;
import com.pss.interfaces.*;
import com.pss.handlers.*;

import javax.vecmath.Vector3d;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a suite for running projectile simulations.
 * The simulations steps are computed, stored, and output to various formats.
 */
public class ProjectileSimulationSuite {

    private static int MAX_SIMSTEPS = 100;
    private static int steps;
    private static IOutputResults[] _resultsOutputers;
    private static ProjectileSimulator _simulator;
    private static List<Vector3d> _results;
    private static Vector3d[] _sim_steps;

    /**
     * The main method of the suite. It initializes the simulation, runs it,
     * and then outputs the results.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        initSimulation();

        for (int t = 0; t < MAX_SIMSTEPS; t++) {
            _sim_steps[t] = new Vector3d(0, 0, 0);
            _sim_steps[t].add(_simulator.updatePosition());
            // Increment steps counter if the z coordinate is non-negative
            if (_sim_steps[t].z >= 0)
                steps++;
            else
                t = MAX_SIMSTEPS;
        }

        // Store the results
        _results = new ArrayList<>(steps);
        for (int i = 0; i < steps; i++) {
            _results.add(_sim_steps[i]);
        }

        outputResults();
    }

    /**
     * Initializes the simulation by getting outputers and creating a new simulator.
     * The precision of the simulator is set by dividing the maximum number of
     * simulation steps set by the precision of the time steps used in computation.
     *
     * The default setting is 100 steps at 0.001 precision which results in 100,000
     * simulation steps total.
     */
    private static void initSimulation() {
        _resultsOutputers = getOutputers();
        _simulator = new MakeProjectileSimulator().createProjectileSimulator(new FileGetConfiguration());
        MAX_SIMSTEPS = (int) (MAX_SIMSTEPS / _simulator.getTimeStep());
        _sim_steps = new Vector3d[MAX_SIMSTEPS];
    }

    /**
     * Returns an array of output result handlers.
     *
     * @return An array of IOutputResults instances
     */
    private static IOutputResults[] getOutputers() {
        IOutputResults[] availableOutputers = {
                new ConsoleOutputer(),
                new ChartOutputer(),
                new ChartOutputer3d(),
                new ChartOutputer3dNonACC()
        };

        return availableOutputers;
    }

    /**
     * Output the results using all available output result handlers.
     */
    private static void outputResults() {
        // For each outputer, output the results
        for (IOutputResults outputer : _resultsOutputers) {
            outputer.outputResults(_results.toArray(new Vector3d[0]), _simulator.getTimeStep());
        }
    }
}
