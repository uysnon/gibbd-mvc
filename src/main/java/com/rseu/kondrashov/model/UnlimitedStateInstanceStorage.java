package com.rseu.kondrashov.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UnlimitedStateInstanceStorage implements Serializable, StateInstanceStorage {
    private static final long serialVersionUID = 1L;

    private StateInstance stateInstance;

    @Override
    public StateInstance tryToGetInstance() {
        return stateInstance;
    }
}
