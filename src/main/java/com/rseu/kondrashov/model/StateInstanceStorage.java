package com.rseu.kondrashov.model;

public interface StateInstanceStorage {
    StateInstance tryToGetInstance();

    default void putInstance(StateInstance instance) {

    }
}
