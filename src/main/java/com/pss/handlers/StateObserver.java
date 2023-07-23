package com.pss.handlers;

import java.util.ArrayList;
import java.util.List;

import com.pss.enums.State;
import com.pss.interfaces.IStateObserver;

public class StateObserver implements IStateObserver {
    private List<State> observedStates = new ArrayList<>();

    @Override
    public void stateChanged(State newState) {
        observedStates.add(newState);
    }

    public List<State> getObservedStates() {
        return observedStates;
    }
}
