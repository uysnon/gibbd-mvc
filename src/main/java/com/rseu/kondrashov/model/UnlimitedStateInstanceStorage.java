package com.rseu.kondrashov.model;

import lombok.Data;

@Data
public class UnlimitedStateInstanceStorage implements StateInstanceStorage {
    private StateInstance stateInstance;

    @Override
    public StateInstance tryToGetInstance() {
        return stateInstance;
    }
}
