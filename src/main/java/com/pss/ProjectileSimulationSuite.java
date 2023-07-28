package com.pss;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

import com.pss.enums.State;
import com.pss.factories.MakeProjectileSimulator;
import com.pss.handlers.ChartOutputer;
import com.pss.handlers.ChartOutputer3d;
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

    public static void main(String[] args) {
        new ProjectileSimulationSuite().context.startSimulation(args);
    }

    public class SimulatorContext extends SimulatorState {

        private static final String FLAG_CHART = "-p";
        private static final String FLAG_CHART_3D = "-p3";
        private static final String FLAG_BOTH = "-pb";
        private static final String FLAG_CHART_3D_ACC = "-pacc";

        private int MAX_SIMSTEPS;
        private MakeProjectileSimulator maker = new MakeProjectileSimulator();
        private ProjectileSimulator _simulator;
        private List<IOutputResults> _resultsOutputers;
        private List<Vector3d> _sim_steps;
        private FileGetConfiguration fileGetConfiguration;

        void startSimulation(String[] args) {
            switch (args.length) {
                case 2:
                    initializeAndRunSimulation(args[0], args[1]);
                    break;
                case 1:
                    initializeAndRunSimulation(args[0], null);
                    break;
                default:
                    initializeAndRunSimulation(null, null);
                    break;
            }
        }

        private void initializeAndRunSimulation(String configPath, String arg) {
            setConfigurationPath(configPath);
            initSimulation(arg);
            runSimulation();
            outputResults();
        }

        void setConfigurationPath(String path) {
            fileGetConfiguration = new FileGetConfiguration(path);
        }

        void initSimulation(String arg) {
            setCurrentState(State.T_SIM_INIT);

            _simulator = maker.createProjectileSimulator(fileGetConfiguration);

            MAX_SIMSTEPS = (int) (_simulator.getMaxStep() / _simulator.getTimeStep());
            _sim_steps = new ArrayList<>(MAX_SIMSTEPS);

            _resultsOutputers = getOutputers(arg);
            setCurrentState(State.SIM_INITIALIZED);
        }

        private List<IOutputResults> getOutputers(String arg) {
            List<IOutputResults> availableOutputers = new ArrayList<>();
            availableOutputers.add(new ConsoleOutputer());

            if (arg != null) {
                switch (arg) {
                    case FLAG_CHART:
                        availableOutputers.add(new ChartOutputer());
                        break;
                    case FLAG_CHART_3D:
                        availableOutputers.add(new ChartOutputer3dNonACC());
                        break;
                    case FLAG_CHART_3D_ACC:
                        availableOutputers.add(new ChartOutputer3d());
                        break;
                    case FLAG_BOTH:
                        availableOutputers.add(new ChartOutputer());
                        availableOutputers.add(new ChartOutputer3dNonACC());
                        break;
                }
            }
            return availableOutputers;
        }

        void runSimulation() {
            setCurrentState(State.T_START_SIM);
            Vector3d currentStep = new Vector3d(0, 0, 0);
            int t = 0;

            System.out.println("\nRUNNING SIMULATION...\n");
            while (t < MAX_SIMSTEPS && currentStep.z >= 0) {
                _sim_steps.add(currentStep);
                currentStep = _simulator.updatePosition();
                setCurrentState(State.T_UPDATE_POSITION);
                t++;
                if (t == MAX_SIMSTEPS && currentStep.z >= 0) {
                    System.out.println("Warning: Simulation was still progressing after " +
                            MAX_SIMSTEPS + " steps. Increasing MAX_SIMSTEPS.\n");
                    MAX_SIMSTEPS = MAX_SIMSTEPS + 1000;
                }
            }
            System.out.println("SIMULATION COMPLETE, use ctrl+c to quit\n");
            setCurrentState(State.SIM_COMPLETE);
        }

        void outputResults() {
            for (IOutputResults outputer : _resultsOutputers) {
                outputer.outputResults(_sim_steps.toArray(new Vector3d[0]), _simulator.getTimeStep());
            }
            setCurrentState(State.SIM_OUTPUT_RESULT);
        }
    }
}
