package com.pss.interfaces;

import com.pss.enums.State;

public interface IStateObserver {
    public void stateChanged(State newState);
}
