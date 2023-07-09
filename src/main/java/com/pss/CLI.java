package com.pss;

import picocli.CommandLine;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

@Command(name = "Sim Config", header = "%n@|green Simulation Configuration|@")
public class CLI implements Runnable {

    @Option(names = { "-i", "--indir" }, required = false, description = "Read simulation config from this directory")
    String inDir;

    @Option(names = { "-o", "--outdir" }, required = false, description = "Write simulation results to this directory")
    String outDir;

    @Option(names = { "-a", "--area" }, required = false, description = "Area of the projectile (m^2)")
    double area;

    @Option(names = { "-d", "--drag" }, required = false, description = "Drag coffecient override")
    double drag;

    @Option(names = { "-g", "--gravity" }, required = false, description = "Gravity override (m/s^2)")
    double gravity;

    @Option(names = { "-m", "--mass" }, required = false, description = "Mass of the projectile (kg)")
    double mass;

    @Option(names = { "-v", "--velocity" }, required = false, description = "Initial velocity of the projectile (m/s)")
    double initialVelocity;

    public void run() {
        System.out.println("Indir is: " + inDir);
        System.out.println("Outdir is: " + outDir);
        System.out.println("Area is: " + area);
        System.out.println("Drag is: " + drag);
        System.out.println("Mass is: " + mass);
        System.out.println("Initial velocity is: " + initialVelocity);
    }

    public static void main(String... args) {
        new CommandLine(new CLI()).execute(args);
    }
}
