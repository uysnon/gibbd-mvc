package com.rseu.kondrashov.model;

import lombok.Data;

import java.util.Stack;

@Data
public class LimitedStateInstanceStorage implements StateInstanceStorage {
    private Stack<StateInstance> stateInstancesPull;

    @Override
    public synchronized StateInstance tryToGetInstance() {
         if (stateInstancesPull.isEmpty()){
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
