package com.pss.interfaces;

import com.pss.enums.State;

public interface IStateObserver {
    void stateChanged(State newState);
}
