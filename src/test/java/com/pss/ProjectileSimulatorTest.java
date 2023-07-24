package com.pss;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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
    private static final String currentDir = System.getProperty("user.dir");
    private static final String EXPECTED_OUTPUT_FILE_PATH = currentDir + "/image.png";
    private static final String EXPECTED_CONSOLE_OUTPUT = "1.00        5.7       11.3       11.4";

    private static boolean test = false;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testSimulationObserved() {
        StateObserver observer = new StateObserver();
        SimulatorState.addObserver(observer);

        // Do not change the contents of simulatorSettingsTest.json without
        // changing this test, otherwise it will break.
        String filePath = "-i simulatorSettingsTest.json";
        String[] args = { filePath };
        // String[] args = {  };
        ProjectileSimulationSuite.main(args);

        List<State> expectedStates = Arrays.asList(State.INIT_SIMULATION,
                State.READ_FILE, State.PARSE_CONFIG, State.STORE_CONFIG,
                State.SIMULATION_INITIALIZED, State.START_SIMULATION,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.UPDATE_POSITION, State.INCREMENT_STEP_COUNTER,
                State.SIMULATION_COMPLETED, State.STORE_RESULTS,
                State.PRINT_RESULTS_TO_CONSOLE,
                State.PREPARE_PLOT_DATA,
                State.SAVE_PLOT_AS_PNG,
                State.OUTPUT_RESULT);

        // Assertions.assertEquals(expectedStates, observer.getObservedStates(),
        //         "The simulation did not go through the expected states");

        // Check that the expected output file was created
        // Assertions.assertTrue(Files.exists(Paths.get(EXPECTED_OUTPUT_FILE_PATH)), "Output file was not created");

        // Check that the expected output was printed to the console
        // if (outContent.toString().contains(EXPECTED_CONSOLE_OUTPUT))
        //     test = true;
        // Assertions.assertTrue(test, "Console output is not as expected");
    }

}
