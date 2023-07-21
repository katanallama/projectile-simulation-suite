package com.pss;

import com.pss.enums.State;

public class SimulatorState {
    private static State currentState;

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State state) {
        currentState = state;
    }
}
