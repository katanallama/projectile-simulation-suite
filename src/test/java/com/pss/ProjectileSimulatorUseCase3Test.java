package com.pss;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.pss.enums.State;
import com.pss.handlers.StateObserver;
import com.pss.utils.TestUtilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import picocli.CommandLine;

@TestInstance(Lifecycle.PER_CLASS)
class ProjectileSimulatorUseCase3Test {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ProjectileSimulationSuite suite;
    private StateObserver observer;

    @BeforeAll
    void setUp() {
        System.setOut(new PrintStream(outContent));
        suite = new ProjectileSimulationSuite();
        suite.context = suite.new SimulatorContext();
        observer = new StateObserver();
        suite.context.addObserver(observer);

        String filePath = "-c testUseCase3";
        String[] args = filePath.split(" ");

        new CommandLine(suite.context).execute(args);
    }

    @Test
    void testSimulationStates() {
        List<State> expectedStates = TestUtilities.getStatesList(58066);

        Assertions.assertEquals(expectedStates, observer.getObservedStates(),
                "The simulation did not go through the expected states");
    }

    @Test
    void testConfiguration() {
        String EXPECTED_CONFIG_STRING = "config/testUseCase3.json";

        Assertions.assertTrue(outContent.toString().contains(EXPECTED_CONFIG_STRING),
                "Configuration is not as expected");
    }

    @Test
    void testMaxStepUpdate() {
        String EXPECTED_OUTPUT = "Warning: Simulation was still progressing after 1000 steps";

        Assertions.assertTrue(outContent.toString().contains(EXPECTED_OUTPUT),
                "MAX_SIMSTEPS did not update as expected");
    }

    @Test
    void testFinalPositionOutput() {
        String EXPECTED_FINAL_POSITION = "58.07        0.0    23533.2        0.0";

        Assertions.assertTrue(outContent.toString().contains(EXPECTED_FINAL_POSITION),
                "Console output is not correct, expected : " + EXPECTED_FINAL_POSITION
                        + " but was : " + outContent.toString());
    }
}
