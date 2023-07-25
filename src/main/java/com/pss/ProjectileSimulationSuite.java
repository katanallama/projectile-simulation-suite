package com.pss;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

import com.pss.enums.State;
import com.pss.factories.MakeProjectileSimulator;
import com.pss.handlers.ChartOutputer;
// import com.pss.handlers.ChartOutputer3d;
import com.pss.handlers.ChartOutputer3dNonACC;
import com.pss.handlers.ConsoleOutputer;
import com.pss.handlers.FileGetConfiguration;
import com.pss.interfaces.IOutputResults;

/**
 * This class provides a suite for running projectile simulations.
 * The simulations steps are computed, stored, and output to various formats.
 */
public class ProjectileSimulationSuite {

    public SimulatorContext context = new SimulatorContext();

    /**
     * The main method of the suite. It initializes the simulation, runs it,
     * and then stores and outputs the results.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        ProjectileSimulationSuite suite = new ProjectileSimulationSuite();

        if (args.length > 0) {
            suite.context.setConfigurationPath(args[0]);
        }

        suite.context.initSimulation();
        suite.context.runSimulation();
        suite.context.storeResults();
        suite.context.outputResults();
    }

    /**
     * The SimulatorContext class manages the state of the simulation.
     */
    public class SimulatorContext extends SimulatorState {

        private int MAX_SIMSTEPS;
        private int steps;
        private IOutputResults[] _resultsOutputers;
        private ProjectileSimulator _simulator;
        private List<Vector3d> _results;
        private Vector3d[] _sim_steps;
        private FileGetConfiguration fileGetConfiguration;
        // private State currentState;

        void setConfigurationPath(String path) {
            fileGetConfiguration = new FileGetConfiguration(path);
        }

        void initSimulation() {
            setCurrentState(State.INIT_SIMULATION);

            _simulator = new MakeProjectileSimulator().createProjectileSimulator(fileGetConfiguration);

            MAX_SIMSTEPS = (int) (_simulator.getMaxStep() / _simulator.getTimeStep());
            // assert(MAX_SIMSTEPS > 1);
            _sim_steps = new Vector3d[MAX_SIMSTEPS];

            _resultsOutputers = getOutputers();
            setCurrentState(State.SIMULATION_INITIALIZED);
        }

        private IOutputResults[] getOutputers() {
            IOutputResults[] availableOutputers = {
                    new ConsoleOutputer(),
                    new ChartOutputer(),
                    // new ChartOutputer3dNonACC(),
                    // new ChartOutputer3d(),
            };

            return availableOutputers;
        }

        void runSimulation() {
            setCurrentState(State.START_SIMULATION);

            for (int t = 0; t < MAX_SIMSTEPS; t++) {
                _sim_steps[t] = new Vector3d(0, 0, 0);
                _sim_steps[t].add(_simulator.updatePosition());

                if (_sim_steps[t].z >= 0) {
                    setCurrentState(State.UPDATE_POSITION);
                    steps++;
                    setCurrentState(State.INCREMENT_STEP_COUNTER);
                }
            }
            setCurrentState(State.SIMULATION_COMPLETED);
        }

        void storeResults() {
            _results = new ArrayList<>(steps);
            for (int i = 0; i < steps; i++) {
                _results.add(_sim_steps[i]);
            }
            setCurrentState(State.STORE_RESULTS);
        }

        void outputResults() {
            for (IOutputResults outputer : _resultsOutputers) {
                System.out.println(outputer);
                System.out.println(_resultsOutputers);
                outputer.outputResults(_results.toArray(new Vector3d[0]), _simulator.getTimeStep());
            }
            setCurrentState(State.OUTPUT_RESULT);
        }
    }
}
