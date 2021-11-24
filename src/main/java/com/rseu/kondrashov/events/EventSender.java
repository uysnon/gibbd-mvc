package com.rseu.kondrashov.events;

import java.util.List;

public interface EventSender {
    List<Listener> getListeners();

    void setListeners(List<Listener> listeners);

    default void sendEvent(Event event) {
        getListeners().forEach(listener -> listener.handleEvent(event));
    }
}
