package com.rseu.kondrashov.model;

import com.rseu.kondrashov.events.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Person implements EventSender {
    private String id;
    private List<Listener> listeners;
    private String name;
    private StateInstance stateInstance;

    public void setStateInstance(StateInstance stateInstance) {
        StateInstance oldStateInstance = this.stateInstance;
        this.stateInstance = stateInstance;
        sendStateInstanceUpdated(oldStateInstance);
    }

    @Override
    public List<Listener> getListeners() {
        return listeners;
    }

    @Override
    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

    public void sendReadyForNextState() {
        sendEvent(new Event(this, EventTags.NEED_UPDATE_STATE, new NeedRefreshEventData("state", stateInstance)));
    }

    private void sendStateInstanceUpdated(StateInstance oldStateInstance) {
        sendEvent(new Event(this,EventTags.NEW_STATE, new UpdateEventData("state", oldStateInstance, stateInstance)));
    }
}
