package com.rseu.kondrashov.model;

import java.util.ArrayDeque;
import java.util.Deque;

public enum States {
    NEW_COMERS_WAITING_STATE("newComersWaitingState", false),
    NEW_COMERS_WINDOW_STATE("newComersWindowState", true),
    WAITING_ROOM_BEFORE_INSPECTION("WaitingBeforeInspectionState", false),
    INSPECTION_STATE("inspectionState", true),
    WAITING_ROOM_AFTER_INSPECTION("WaitingAfterInspectionState", false),
    REPEATED_COMERS_STATE("RepeatedComersState", true);

    private String id;
    private boolean isShared;

    States(String id, boolean isShared) {
        this.id = id;
        this.isShared = isShared;
    }

    public String getId() {
        return id;
    }

    public boolean isShared() {
        return isShared;
    }

    public State createState() {
        State state = new State() {
            @Override
            public boolean isShared() {
                return isShared;
            }
        };
        state.setId(id);
        return state;
    }

    public StateInstanceStorage createInstanceStorage(State state, int instanceCount, double speedPerMs, double totalWork) {
        StateInstanceStorage stateInstanceStorage;
        if (!isShared) {
            StateInstance stateInstance = new StateInstance(state, speedPerMs, totalWork);
            stateInstanceStorage = new UnlimitedStateInstanceStorage(stateInstance);
        } else {
            Deque<StateInstance> stateInstances = new ArrayDeque<>();
            for (int i = 0; i < instanceCount; i++) {
                stateInstances.push(new StateInstance(state, speedPerMs, totalWork));
            }
            stateInstanceStorage = new LimitedStateInstanceStorage(stateInstances);
        }
        return stateInstanceStorage;
    }
}
