package com.rseu.kondrashov.model;

import lombok.Data;

@Data
public abstract class State {
    private String id;
    public abstract boolean isShared();
}
