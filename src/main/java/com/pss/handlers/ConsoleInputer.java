package com.pss.handlers;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

import picocli.CommandLine;
import com.pss.enums.Settings;
import com.pss.interfaces.IGetConfiguration;
import java.util.HashMap;

@Command(name = "Sim Config", header = "%n@|green Simulation Configuration|@")
public class ConsoleInputer extends BaseGetConfiguration implements Runnable {

    @Option(names = { "-i", "--indir" }, required = false, description = "Read simulation config from this file")
    String inFile;

    @Option(names = { "-o", "--outdir" }, required = false, description = "Write simulation results to this directory")
    String outFile;

    @Option(names = { "-p", "--projectileArea" }, required = false, description = "Area of the projectile (m^2)")
    double projectileArea;

    @Option(names = { "-d", "--dragCoefficent" }, required = false, description = "Drag coefficient override")
    double dragCoefficent;

    @Option(names = { "-g", "--gravity" }, required = false, description = "Gravity override (m/s^2)")
    double gravity;

    @Option(names = { "-w", "--weight" }, required = false, description = "Weight of the projectile (kg)")
    double weight;

    @Option(names = { "-f", "--initialForce" }, required = false, description = "Initial force of the projectile (N)")
    double initialForce;

    @Option(names = { "-c",
            "--continualForce" }, required = false, description = "Continual force of the projectile (N)")
    double continualForce;

    @Option(names = { "-x",
            "--initialDirection" }, required = false, description = "Initial direction of the projectile as a string of comma separated values: x,y,z")
    String initialDirection;

    @Option(names = { "-r", "--fluidRho" }, required = false, description = "Fluid density (kg/m^3)")
    double fluidRho;

    @Option(names = { "-m", "--maxStep" }, required = false, description = "Maximum number of simulation steps")
    double maxStep;

    @Option(names = { "-t", "--timeStep" }, required = false, description = "Time step for the simulation (s)")
    double timeStep;


    public void run() {
        // overrideSetting(Settings.ProjectileArea.getName(), projectileArea);
        // overrideSetting(Settings.DragCoefficent.getName(), dragCoefficent);
        // overrideSetting(Settings.Gravity.getName(), gravity);
        // overrideSetting(Settings.Weight.getName(), weight);
        // overrideSetting(Settings.InitialForce.getName(), initialForce);
        // overrideSetting(Settings.ContinualForce.getName(), continualForce);
        // overrideSetting(Settings.InitialDirection.getName(), initialDirection);
        // overrideSetting(Settings.FluidRho.getName(), fluidRho);
        overrideSetting(Settings.MaxStep.getName(), maxStep);
        // overrideSetting(Settings.TimeStep.getName(), timeStep);
        // overrideSetting(Settings.InputFile.getName(), inFile);
        // overrideSetting(Settings.OutputFile.getName(), outFile);
    }

    public static void main(String... args) {
        new CommandLine(new ConsoleInputer()).execute(args);
    }

}
