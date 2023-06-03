package com.example.project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import picocli.CommandLine;
import picocli.CommandLine.MissingParameterException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CliTests {

  @Test
  public void testConfig() {
    Cli example = new Cli();
    example.inDir = "config";
    example.outDir = "run";

    // Redirecting System.out to capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Running the Example's run method
    example.run();

    // Restoring the standard System.out
    System.setOut(System.out);

    // Verifying the output
    String expectedOutput = "Indir is: config\n" + System.lineSeparator() + "Outdir is: run" + System.lineSeparator();
    assertEquals(expectedOutput, outputStream.toString(), "Incorrect output directory");
  }

  @Test
  public void testMissingDirectories() {
    Cli example = new Cli();

    // Redirecting System.out to capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Running the Example's run method
    try {
      CommandLine.populateCommand(example);
      example.run();
      fail("Expected exception was not thrown.");
    } catch (MissingParameterException e) {
      // Verifying the exception message
      String expectedErrorMessage = "Missing required options: '--indir=<inDir>', '--outdir=<outDir>'";
      assertEquals(expectedErrorMessage, e.getMessage(), "Incorrect error message");
    }

    // Restoring the standard System.out
    System.setOut(System.out);
  }
}
