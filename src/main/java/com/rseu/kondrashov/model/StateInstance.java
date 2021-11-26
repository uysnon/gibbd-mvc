package com.rseu.kondrashov.model;

import lombok.Data;

@Data
public class StateInstance {
    private State state;
    private double speedPerMs;
    private double totalWork;
    private double completedWork;

    public StateInstance(State state, double speedPerMs, double totalWork) {
        this.state = state;
        this.speedPerMs = speedPerMs;
        this.totalWork = totalWork;
    }

    public boolean isCompleted() {
        return completedWork >= totalWork;
    }

    public void makeWork(int ms) {
        completedWork += speedPerMs * ms;
    }

    public void refresh() {
        completedWork = 0;
    }

}
