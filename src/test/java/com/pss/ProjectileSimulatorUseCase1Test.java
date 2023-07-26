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

class ProjectileSimulatorUseCase1Test {

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
    void testUseCase1() {
        String EXPECTED_FINAL_POSITION = "1.50        8.3        0.4        1.4";
        String EXPECTED_CONFIG_STRING = "config/testUseCase1.json";
        ProjectileSimulationSuite suite = new ProjectileSimulationSuite();
        suite.context = suite.new SimulatorContext();

        StateObserver observer = new StateObserver();
        suite.context.addObserver(observer);

        String filePath = "testUseCase1";
        String[] args = { filePath };

        if (args.length > 0) {
            suite.context.setConfigurationPath(args[0]);
        }

        suite.context.initSimulation();
        suite.context.runSimulation();
        suite.context.storeResults();
        suite.context.outputResults();

        List<State> expectedStates = getStatesList(1500); // 1.5 / 0.001 = 1500

        Assertions.assertEquals(expectedStates, observer.getObservedStates(),
                "The simulation did not go through the expected states");

        // Check that the expected configuration was printed to the console
        Assertions.assertTrue(outContent.toString().contains(EXPECTED_CONFIG_STRING),
                "Configuration is not as expected");

        // // Check that the expected position was printed to the console
        Assertions.assertTrue(outContent.toString().contains(EXPECTED_FINAL_POSITION),
                "Console output is not correct, expected : " + EXPECTED_FINAL_POSITION
                        + " but was : " + outContent.toString());
    }

    static List<State> getStatesList(int repeatCount) {
        List<State> expectedStates = new ArrayList<>(Arrays.asList(
                State.INIT_SIMULATION,
                // State.READ_FILE,
                // State.PARSE_CONFIG,
                // State.STORE_CONFIG,
                // State.INIT_SIMULATION,
                State.SIMULATION_INITIALIZED,
                State.START_SIMULATION));

        for (int i = 0; i < repeatCount; i++) {
            expectedStates.add(State.UPDATE_POSITION);
            expectedStates.add(State.INCREMENT_STEP_COUNTER);
        }

        expectedStates.addAll(Arrays.asList(
                State.SIMULATION_COMPLETED,
                State.STORE_RESULTS,
                // State.STORE_RESULTS
                // State.PRINT_RESULTS_TO_CONSOLE,
                // State.PREPARE_PLOT_DATA,
                // State.SAVE_PLOT_AS_PNG,
                State.OUTPUT_RESULT));
        // ));

        return expectedStates;
    }

}
