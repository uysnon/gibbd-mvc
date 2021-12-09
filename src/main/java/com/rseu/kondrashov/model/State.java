package com.rseu.kondrashov.model;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class State implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    public abstract boolean isShared();
}
