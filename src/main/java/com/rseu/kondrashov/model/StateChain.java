package com.rseu.kondrashov.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StateChain {
    private List<State> availableStates;

    public State getNext(State state) {
        for (int i = 0; i < availableStates.size() - 1; i++) {
            if (availableStates.get(i).getId().equals(state.getId())) {
                return availableStates.get(i + 1);
            }
        }
        if (availableStates.get(availableStates.size() - 1).getId().equals(state.getId())) {
            return null;
        }
        throw new IllegalArgumentException("cannot find state in chain: " + state);
    }
}
