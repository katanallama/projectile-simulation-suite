package com.pss;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

import com.pss.enums.State;
import com.pss.factories.MakeProjectileSimulator;
import com.pss.handlers.ChartOutputer;
import com.pss.handlers.ChartOutputer3dNonACC;
import com.pss.handlers.ConsoleOutputer;
import com.pss.handlers.FileGetConfiguration;
import com.pss.interfaces.IOutputResults;

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
     * and then stores and outputs the results.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        initSimulation();
        //runSimulation();
        //storeResults();
        //outputResults();
    }

    /**
     * Stores the results of the simulation. Create a new list to store the results
     * and adds the simulation steps to the results list.
     */
    private static void storeResults() {
        _results = new ArrayList<>(steps);
        for (int i = 0; i < steps; i++) {
            _results.add(_sim_steps[i]);
        }
        SimulatorState.setCurrentState(State.STORE_RESULTS);
    }

    /**
     * Runs the simulation for the set number of steps.
     */
    private static void runSimulation() {
        SimulatorState.setCurrentState(State.START_SIMULATION);
        for (int t = 0; t < MAX_SIMSTEPS; t++) {
            _sim_steps[t] = new Vector3d(0, 0, 0);
            _sim_steps[t].add(_simulator.updatePosition());
            SimulatorState.setCurrentState(State.UPDATE_POSITION);

            if (_sim_steps[t].z >= 0) {
                steps++;
                SimulatorState.setCurrentState(State.INCREMENT_STEP_COUNTER);
            }
        }
        SimulatorState.setCurrentState(State.SIMULATION_COMPLETED);
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
        SimulatorState.setCurrentState(State.INIT_SIMULATION);
        _simulator = new MakeProjectileSimulator().createProjectileSimulator(new FileGetConfiguration());
        MAX_SIMSTEPS = (int) (_simulator.getMaxStep() / _simulator.getTimeStep());
        _sim_steps = new Vector3d[MAX_SIMSTEPS];

        _resultsOutputers = getOutputers();
        SimulatorState.setCurrentState(State.SIMULATION_INITIALIZED);
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
                new ChartOutputer3dNonACC()
                // new ChartOutputer3d(),
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
        SimulatorState.setCurrentState(State.OUTPUT_RESULT);
    }
}
