package com.example.project;

import picocli.CommandLine;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

@Command(name = "Sim Config", header = "%n@|green Simulation Configuration|@")
public class Cli implements Runnable {

  @Option(names = { "-i", "--indir" }, required = true, description = "Read simulation config from this directory.")
  String inDir;

  @Option(names = { "-o", "--outdir" }, required = true, description = "Write simulation results to this directory.")
  String outDir;

  public void run() {
    System.out.println("Indir is: " + inDir + "\n");
    System.out.println("Outdir is: " + outDir);
  }

  public static void main(String... args) {
    new CommandLine(new Cli()).execute(args);
  }
}
