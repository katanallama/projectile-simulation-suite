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
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * The main class of the projectile simulation suite which coordinates the
 * simulation process. This class primarily sets up the simulation context
 * and initiates the simulation. It relies on nested classes and external
 * handlers to manage the actual simulation and data output.
 */
@Command(name = "ProjectileSimulationSuite", mixinStandardHelpOptions = true, description = "Simulates the path of a projectile.")
public class ProjectileSimulationSuite {

    public SimulatorContext context = new SimulatorContext();

    /**
     * The main method to start the simulation. This method creates an instance of
     * the ProjectileSimulationSuite class and triggers the simulation by calling
     * the startSimulation method on the context with the provided arguments.
     *
     * @param args An array of command-line arguments
     */
    public static void main(String[] args) {
        ProjectileSimulationSuite pss = new ProjectileSimulationSuite();
        SimulatorContext context = pss.new SimulatorContext();
        new CommandLine(context).execute(args);
    }

    /**
     * Nested class extending the SimulatorState class. This class is responsible
     * for controlling the simulation state and process. Handles the initialization
     * of the simulation based on configuration files, execution of the simulation
     * according to set parameters, and directing the output of the simulation
     * results.
     */
    @Command(name = "simulate", description = "Start the simulation.")
    public class SimulatorContext extends SimulatorState implements Runnable {

        @Option(names = { "-p", "--plot" }, description = "Plotting options")
        private String plotOption;

        @Option(names = { "-q", "--quiet" }, description = "Do not output all steps to console")
        private boolean quiet;

        @Option(names = { "-c", "--config" }, description = "Use configuration <configName>.json in ./config")
        private String configName;

        private int MAX_SIMSTEPS;
        private MakeProjectileSimulator maker = new MakeProjectileSimulator();
        private ProjectileSimulator _simulator;
        private List<IOutputResults> _resultsOutputers;
        private List<Vector3d> _result;
        private FileGetConfiguration fileGetConfiguration;

        @Override
        public void run() {
            initializeAndRunSimulation();
        }

        /**
         * Main handler for running a simulation. This method controls the process of
         * setting the configuration path, initializing the simulation, running the
         * simulation, and outputting results.
         *
         * @param configName The path to the configuration file
         * @param arg        Command line argument that dictates the type of output
         */
        private void initializeAndRunSimulation() {
            setConfigurationPath();
            initSimulation();
            runSimulation();
            outputResults();
        }

        /**
         * Sets the path to the configuration file to be used. Assumes that the .json
         * file is in ./config/
         *
         * @param name Configuration file name
         */
        void setConfigurationPath() {
            setCurrentState(State.T_SIM_INIT); // transisition from start
            fileGetConfiguration = new FileGetConfiguration(configName);
        }

        /**
         * Initializes the simulation environment according to the provided argument and
         * configuration file. This includes creating the projectile simulator,
         * determining the maximum number of simulation steps, creating the list of
         * simulation results, and establishing the results outputers. The maximum
         * number of simulation steps is scaled on the precision of the simulation set
         * by the time step.
         *
         * @param option Output option: 2D plotting, 3D plotting, 3D GPU accelerated
         *               plotting with OpenGL
         */
        void initSimulation() {
            _simulator = maker.createProjectileSimulator(fileGetConfiguration);
            _resultsOutputers = getOutputers();
            MAX_SIMSTEPS = (int) (_simulator.getMaxStep() / _simulator.getTimeStep());
            _result = new ArrayList<>(MAX_SIMSTEPS);

            setCurrentState(State.SIM_INITIALIZED);
            setCurrentState(State.T_START_SIM); // transition to running state
        }

        /**
         * Returns a list of IOutputResults objects based on the provided argument. This
         * method will always return a list with at least a ConsoleOutputer. If an
         * argument is provided, additional outputers are added to the list.
         *
         * @param option Command line option that dictates the type of output
         * @return List of IOutputResults objects
         */
        private List<IOutputResults> getOutputers() {
            List<IOutputResults> availableOutputers = new ArrayList<>();
            if (!quiet)
                availableOutputers.add(new ConsoleOutputer());

            if (plotOption != null) { // Avoid NullPointerException if no plotOption is provided
                switch (plotOption) {
                    case "2d":
                        availableOutputers.add(new ChartOutputer());
                        break;
                    case "3d":
                        availableOutputers.add(new ChartOutputer3dNonACC());
                        break;
                    case "acc3d":
                        availableOutputers.add(new ChartOutputer3d());
                        break;
                    case "both":
                        availableOutputers.add(new ChartOutputer());
                        availableOutputers.add(new ChartOutputer3dNonACC());
                        break;
                }
            }
            return availableOutputers;
        }

        /**
         * Executes the simulation according to the parameters set in the
         * initialization. During execution, the simulation results are stored in a
         * list. If the configured maximum number of simulation steps is reached while
         * the projectile is still in motion, the number of steps is increased and a
         * warning is issued.
         */
        void runSimulation() {
            Vector3d currentStep = new Vector3d(0, 0, 0);
            int t = 0;

            System.out.println("\nRUNNING SIMULATION...\n");
            while (t < MAX_SIMSTEPS && currentStep.z >= 0) {
                _result.add(currentStep);
                currentStep = _simulator.updatePosition();
                setCurrentState(State.T_UPDATE_POSITION);
                t++;
                if (t == MAX_SIMSTEPS && currentStep.z >= 0) {
                    int scaledSteps = (int) (10 / _simulator.getTimeStep());
                    System.out.println("Warning: Simulation was still progressing after " +
                            MAX_SIMSTEPS + " steps. Increasing by " + scaledSteps + " steps\n");
                    MAX_SIMSTEPS = MAX_SIMSTEPS + scaledSteps;
                }
            }
            setCurrentState(State.SIM_COMPLETE);
        }

        /**
         * Handles the output of simulation results. This includes passing the
         * simulation results and time step to each IOutputResults object in the list of
         * results outputers.
         */
        void outputResults() {
            for (IOutputResults outputer : _resultsOutputers) {
                outputer.outputResults(_result.toArray(new Vector3d[0]), _simulator.getTimeStep());
            }
            setCurrentState(State.SIM_OUTPUT_RESULT);
            System.out.println("\nSIMULATION COMPLETE, use ctrl+c to quit\n");
        }
    }
}
