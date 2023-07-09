package com.pss;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import picocli.CommandLine;
import picocli.CommandLine.MissingParameterException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CLITest {

  @Test
  public void testOutDirConfig() {
    CLI testOut = new CLI();
    testOut.outDir = "run";

    // Redirecting System.out to capture the output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    testOut.run();
    System.setOut(System.out);

    String expectedOutput = "Outdir is: run" + System.lineSeparator();
    assertTrue(outputStream.toString().contains(expectedOutput), "Incorrect output directory");
  }

  // @Test
  // public void testMissingDirectories() {
  //   CLI testMissing = new CLI();

  //   // Redirecting System.out to capture the output
  //   ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  //   System.setOut(new PrintStream(outputStream));

  //   try {
  //     CommandLine.populateCommand(testMissing);
  //     testMissing.run();
  //     fail("Expected exception was not thrown.");
  //   } catch (MissingParameterException e) {
  //     String expectedErrorMessage = "Missing required options: '--indir=<inDir>', '--outdir=<outDir>'";
  //     assertEquals(expectedErrorMessage, e.getMessage(), "Incorrect error message");
  //   }

  //   System.setOut(System.out);
  // }
}
