package com.rseu.kondrashov.model;

import com.rseu.kondrashov.events.Event;
import com.rseu.kondrashov.events.EventTags;
import com.rseu.kondrashov.events.Listener;
import lombok.Data;

import java.util.Map;

@Data
public class Gibbd implements Listener {
    private StateChain stateChain;
    private Map<State, StateInstanceStorage> stateStoragesMap;

    @Override
    public void handleEvent(Event event) {
        if (EventTags.NEED_UPDATE_STATE.equals(event.getTag())) {
            Person person = (Person) event.getSender();
            tryGivePersonNewState(person);
        }
    }

    private synchronized void tryGivePersonNewState(Person person) {
        StateInstance currentStateInstance = person.getStateInstance();
        State currentState = currentStateInstance.getState();
        State nextState = stateChain.getNext(currentState);
        if (nextState != null) {
            StateInstance nextStateInstance = stateStoragesMap.get(nextState).tryToGetInstance();
            if (nextStateInstance != null) {
                currentStateInstance.refresh();
                stateStoragesMap.get(currentState).putInstance(currentStateInstance);
                person.setStateInstance(nextStateInstance);
            }
        } else {
            currentStateInstance.refresh();
            stateStoragesMap.get(currentState).putInstance(currentStateInstance);
            person.setStateInstance(null);
        }
    }

}
