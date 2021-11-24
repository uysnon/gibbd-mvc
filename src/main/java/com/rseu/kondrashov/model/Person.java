package com.rseu.kondrashov.model;

import com.rseu.kondrashov.events.*;
import lombok.Data;

import java.util.List;

@Data
public class Person implements EventSender {
    private String id;
    private List<Listener> listeners;
    private String name;
    private StateInstance stateInstance;


    @Override
    public List<Listener> getListeners() {
        return listeners;
    }

    @Override
    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

    public void sendReadyForNextState() {
        sendEvent(new Event(id, new NeedRefreshEventData("state", stateInstance)));
    }


    private void sendStateInstanceUpdated(StateInstance oldStateInstance) {
        sendEvent(new Event(id, new UpdateEventData("state", oldStateInstance, stateInstance)));
    }
}
