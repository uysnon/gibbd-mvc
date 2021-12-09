package com.rseu.kondrashov.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Deque;

@Data
@AllArgsConstructor
public class LimitedStateInstanceStorage implements Serializable, StateInstanceStorage {
    private static final long serialVersionUID = 1L;

    private Deque<StateInstance> stateInstancesPull;

    @Override
    public synchronized StateInstance tryToGetInstance() {
        if (stateInstancesPull.isEmpty()) {
            return null;
        } else {
            return stateInstancesPull.pop();
        }
    }


    @Override
    public synchronized void putInstance(StateInstance instance) {
        stateInstancesPull.push(instance);
    }
}
