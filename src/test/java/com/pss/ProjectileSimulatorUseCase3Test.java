package com.pss;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.pss.enums.State;
import com.pss.handlers.StateObserver;
import com.pss.utils.TestUtilities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectileSimulatorUseCase3Test {

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
    void testUseCase3() {
        ProjectileSimulationSuite suite = new ProjectileSimulationSuite();
        suite.context = suite.new SimulatorContext();

        StateObserver observer = new StateObserver();
        suite.context.addObserver(observer);

        String EXPECTED_OUTPUT = "Warning: Simulation was still progressing after " + 1000
                + " steps. Increasing MAX_SIMSTEPS.";

        String filePath = "testUseCase3";
        String EXPECTED_CONFIG_STRING = "config/" + filePath + ".json";
        String[] args = { filePath };

        suite.context.startSimulation(args);

        List<State> expectedStates = TestUtilities.getStatesList(58066);

        Assertions.assertEquals(expectedStates, observer.getObservedStates(),
                "The simulation did not go through the expected states");

        // Check that the expected configuration was printed to the console
        Assertions.assertTrue(outContent.toString().contains(EXPECTED_CONFIG_STRING),
                "Configuration is not as expected");

        // Check that the expected position was printed to the console
        Assertions.assertTrue(outContent.toString().contains(EXPECTED_OUTPUT),
                "Console output is not correct, expected : " + EXPECTED_OUTPUT
                        + " but was : " + outContent.toString());
    }


}
