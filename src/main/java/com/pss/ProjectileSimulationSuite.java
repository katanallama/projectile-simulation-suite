package com.pss;

import com.pss.factories.*;
import com.pss.interfaces.*;
import com.pss.handlers.*;

import javax.vecmath.Vector3d;

public class ProjectileSimulationSuite {
    private static Vector3d[] _results;
    private static IOutputResults[] _resultsOutputers;
    private static final int MAX_OUTPUTERS = 1;
    private static IGetConfiguration _configHandler;
    private static ProjectileSimulator _simulator;

    public static void main(String[] args) {
        initSimulation();

        int maxTime = 1000; // todo get from config

        for (int t = 0; t < maxTime; t++) {
            _results[t] = new Vector3d(0, 0, 0);
            _results[t].add(_simulator.updatePosition());
        }

        outputResults();
    }

    private static void initSimulation() {
        int maxSimulationTime = 1000; //Todo get this via config

        _results = new Vector3d[maxSimulationTime];
        _resultsOutputers = getOutputers();
        _configHandler = new StubGetConfiguration();

        _simulator = new MakeProjectileSimulator().createProjectileSimulator(_configHandler);
    };

    private static IOutputResults[] getOutputers() {
        IOutputResults[] availableOutputers = new IOutputResults[MAX_OUTPUTERS];
        availableOutputers[0] = new ConsoleOutputer();

        // TODO Get this via config
        IOutputResults[] outputers = new IOutputResults[getOuputerAmount()];
        outputers[0] = availableOutputers[0];
        return outputers;
    }

    private static int getOuputerAmount() {
        return 1;
    }

    private static void outputResults() {
        for(int i = 0; i < _resultsOutputers.length; i++) {
            _resultsOutputers[i].outputResults(_results);
        }
    }
}
