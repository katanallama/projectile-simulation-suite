package com.pss;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pss.enums.State;
import com.pss.utils.TestUtilities;
import com.pss.handlers.StateObserver;

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
        suite.context.addObserver(observer);

        String EXPECTED_CONFIG_STRING = "Config file not found at config/testUseCase0.json, using default settings";

        String filePath = "testUseCase0";
        String[] args = { filePath };

        suite.context.startSimulation(args);

        List<State> expectedStates = TestUtilities.getStatesList(58066);

        Assertions.assertEquals(expectedStates, observer.getObservedStates(),
                "The simulation did not go through the expected states");

        // Check that the expected configuration was printed to the console
        Assertions.assertTrue(outContent.toString().contains(EXPECTED_CONFIG_STRING),
                "Configuration is not as expected");


        Assertions.assertTrue(outContent.toString().contains(EXPECTED_CONFIG_STRING), "Failed in testUseCase0: Configuration is not as expected");
    }


}
