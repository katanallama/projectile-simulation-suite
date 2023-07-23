package com.pss;

import java.util.ArrayList;
import java.util.List;

import com.pss.enums.State;
import com.pss.interfaces.IStateObserver;

public class SimulatorState {
    private static State currentState;
    private static List<IStateObserver> observers = new ArrayList<>();

    public static void setCurrentState(State state) {
        currentState = state;
        notifyObservers();
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void addObserver(IStateObserver observer) {
        observers.add(observer);
    }

    private static void notifyObservers() {
        for (IStateObserver observer : observers) {
            observer.stateChanged(currentState);
        }
    }
}
