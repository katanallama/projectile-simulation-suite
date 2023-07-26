package com.pss;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.pss.enums.State;
import com.pss.handlers.StateObserver;
import com.pss.interfaces.IStateObserver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectileSimulatorUseCase0Test {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testUseCase0() {
        ProjectileSimulationSuite suite = new ProjectileSimulationSuite();
        suite.context = suite.new SimulatorContext();

        StateObserver observer = new StateObserver();
        // SimulatorState.addObserver(observer);
        suite.context.addObserver(observer);

        String EXPECTED_CONFIG_STRING = "Config file not found at config/testUseCase0.json, using default settings";

        String filePath = "testUseCase0";
        String[] args = { filePath };

        if (args.length > 0) {
            suite.context.setConfigurationPath(args[0]);
        }

        suite.context.initSimulation();
        suite.context.runSimulation();
        suite.context.storeResults();
        suite.context.outputResults();

        List<State> expectedStates = new ArrayList<>(Arrays.asList(
                State.INIT_SIMULATION,
                State.SIMULATION_INITIALIZED));
        // List<State> expectedStates = new ArrayList<>(Arrays.asList(
        // State.INIT_SIMULATION,
        // State.SIMULATION_INITIALIZED,
        // State.STORE_RESULTS,
        // State.OUTPUT_RESULT));

        // Assertions.assertEquals(expectedStates, observer.getObservedStates(), "Failed in testUseCase0: The simulation did not go through the expected states");

        Assertions.assertTrue(outContent.toString().contains(EXPECTED_CONFIG_STRING), "Failed in testUseCase0: Configuration is not as expected");
    }

}
