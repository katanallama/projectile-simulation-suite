package com.pss;

import com.pss.factories.*;
import com.pss.interfaces.*;
import com.pss.handlers.*;

import javax.vecmath.Vector3d;

public class ProjectileSimulationSuite {
    private static Vector3d[] _results;
    private static IOutputResults[] _resultsOutputers;
    private static final int MAX_OUTPUTERS = 4;
    // private static final int MAX_OUTPUTERS = 3;
    private static final int MAX_SIMSTEPS = 80;
    private static IGetConfiguration _configHandler;
    private static ProjectileSimulator _simulator;

    public static void main(String[] args) {
        initSimulation();

        for (int t = 0; t < MAX_SIMSTEPS; t++) {
            _results[t] = new Vector3d(0, 0, 0);
            _results[t].add(_simulator.updatePosition());
        }
        
        outputResults();
    }

    private static void initSimulation() {
        _results = new Vector3d[MAX_SIMSTEPS];
        _resultsOutputers = getOutputers();
        _configHandler = new FileGetConfiguration();

        _simulator = new MakeProjectileSimulator().createProjectileSimulator(_configHandler);
    };

    private static IOutputResults[] getOutputers() {
        IOutputResults[] availableOutputers = new IOutputResults[MAX_OUTPUTERS];
        availableOutputers[0] = new ConsoleOutputer();
        availableOutputers[1] = new ChartOutputer();
        availableOutputers[2] = new ChartOutputer3d();
        availableOutputers[3] = new ChartOutputer3dNonACC();

        // TODO Get this via config
        IOutputResults[] outputers = new IOutputResults[getOutputerAmount()];
        outputers[0] = availableOutputers[0];
        outputers[1] = availableOutputers[1];
        outputers[2] = availableOutputers[2];
        outputers[3] = availableOutputers[3];
        return outputers;
    }

    private static int getOutputerAmount() {
        // return 3;
        return 4;
    }

    private static void outputResults() {
        for(int i = 0; i < _resultsOutputers.length; i++) {
            _resultsOutputers[i].outputResults(_results);
        }
    }
}
