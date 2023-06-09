package com.pss.handlers;

import javax.vecmath.Vector3d;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleOutputerTest {

    @Test
    void outputResults() {
        Vector3d[] testResults = new Vector3d[1];
        testResults[0] = new Vector3d(1, 2, 3);
        ConsoleOutputer consoleOutputer = new ConsoleOutputer();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        consoleOutputer.outputResults(testResults);
        System.setOut(System.out);

        String expectedOutput = "(1.0, 2.0, 3.0)\n";
        assertEquals(expectedOutput, outputStream.toString(), "Incorrect output");
    }
}
