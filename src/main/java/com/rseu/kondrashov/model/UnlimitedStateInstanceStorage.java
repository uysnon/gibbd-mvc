package com.rseu.kondrashov.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnlimitedStateInstanceStorage implements StateInstanceStorage {
    private StateInstance stateInstance;

    @Override
    public StateInstance tryToGetInstance() {
        return stateInstance;
    }
}
