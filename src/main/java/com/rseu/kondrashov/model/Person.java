package com.rseu.kondrashov.model;

import com.rseu.kondrashov.events.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Person implements Serializable, EventSender {
    private static final long serialVersionUID = 1L;
    private String id;
    private transient List<Listener> listeners;
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


    public void setStateInstance(StateInstance stateInstance) {
        StateInstance oldStateInstance = this.stateInstance;
        this.stateInstance = stateInstance;
        sendStateInstanceUpdated(oldStateInstance);
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void sendReadyForNextState() {
        sendEvent(new Event(this, EventTags.NEED_UPDATE_STATE, new NeedRefreshEventData("state", stateInstance)));
    }

    private void sendStateInstanceUpdated(StateInstance oldStateInstance) {
        sendEvent(new Event(this, EventTags.NEW_STATE, new UpdateEventData("state", oldStateInstance, stateInstance)));
    }
}
