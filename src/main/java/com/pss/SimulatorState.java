package com.pss;

import java.util.ArrayList;
import java.util.List;

import com.pss.enums.State;
import com.pss.interfaces.IStateObserver;

public class SimulatorState {
    private State currentState;
    private List<IStateObserver> observers = new ArrayList<>();

    public void setCurrentState(State state) {
        currentState = state;
        notifyObservers();
    }

    public State getCurrentState() {
        return currentState;
    }

    public void addObserver(IStateObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (IStateObserver observer : observers) {
            observer.stateChanged(currentState);
        }
    }
}
