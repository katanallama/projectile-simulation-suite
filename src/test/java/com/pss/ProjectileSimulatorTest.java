package com.pss;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.pss.enums.State;
import com.pss.handlers.StateObserver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectileSimulatorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ProjectileSimulationSuite.SimulatorContext context = new ProjectileSimulationSuite.SimulatorContext();

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

        StateObserver observer = new StateObserver();
        SimulatorState.addObserver(observer);
        String filePath = "testUseCase1";
        String[] args = { filePath };
        ProjectileSimulationSuite.main(args);

        // UPDATE_POSITION and INCREMENT_STEP_COUNTER should be repeated
        int repeatCount = 1500; // 1.5 / 0.001 = 1500

        List<State> expectedStates = new ArrayList<>(Arrays.asList(
                State.INIT_SIMULATION,
                State.READ_FILE,
                State.PARSE_CONFIG,
                State.STORE_CONFIG,
                State.SIMULATION_INITIALIZED,
                State.START_SIMULATION));

        // expected number of UPDATE_POSITION and INCREMENT_STEP_COUNTER
        for (int i = 0; i < repeatCount; i++) {
            expectedStates.add(State.UPDATE_POSITION);
            expectedStates.add(State.INCREMENT_STEP_COUNTER);
        }

        expectedStates.addAll(Arrays.asList(
                State.SIMULATION_COMPLETED,
                State.STORE_RESULTS,
                State.PRINT_RESULTS_TO_CONSOLE,
                State.PREPARE_PLOT_DATA,
                State.SAVE_PLOT_AS_PNG,
                State.OUTPUT_RESULT)
                              );

        Assertions.assertEquals(expectedStates, observer.getObservedStates(),
                "The simulation did not go through the expected states");

        // Check that the expected configuration was printed to the console
        Assertions.assertTrue(outContent.toString().contains(EXPECTED_CONFIG_STRING),
                "Configuration is not as expected");

        // Check that the expected position was printed to the console
        Assertions.assertTrue(outContent.toString().contains(EXPECTED_FINAL_POSITION),
                "Console output is not as expected");
    }

    @Test
    void testUseCase0() {
        String EXPECTED_CONFIG_STRING = "Config file not found at config/testUseCase0.json, using default settings";

        String filePath = "testUseCase0";
        String[] args = { filePath };

        // ProjectileSimulationSuite.main(args);
        if (args.length > 0) {
            context.setConfigurationPath(args[0]);
        }

        // this will fail on init
        context.initSimulation();


        // Check that the expected configuration was printed to the console
        Assertions.assertTrue(outContent.toString().contains(EXPECTED_CONFIG_STRING),
                "Configuration is not as expected");
    }


}
