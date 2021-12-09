package com.rseu.kondrashov.model;

import com.rseu.kondrashov.events.Event;
import com.rseu.kondrashov.events.EventTags;
import com.rseu.kondrashov.events.Listener;
import com.rseu.kondrashov.events.UpdateEventData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Game implements Serializable, Listener {
    private static final long serialVersionUID = 1L;

    private Map<String, PersonProcess> personProcesses;
    private Gibbd gibbd;

    public void startProcess() {
        personProcesses.values().forEach(Thread::start);
    }

    @Override
    public synchronized void handleEvent(Event event) {
        handlePersonFinished(event);
    }

    public void addPerson(PersonProcess personProcess) {
        personProcesses.put(
                personProcess.getPerson().getId(),
                personProcess
        );
        personProcess.start();
    }

    private void handlePersonFinished(Event event) {
        if (EventTags.NEW_STATE.equals(event.getTag())
                && ((UpdateEventData) event.getData()).getNewValue() == null) {
            personProcesses.remove(((Person) event.getSender()).getId());
        }
    }

    public void cleanInActivePersons() {
        personProcesses
                .values()
                .stream()
                .filter(p -> !p.isPersonActive())
                .map(p -> p.getPerson().getId())
                .collect(Collectors.toList())
                .forEach(id -> personProcesses.remove(id));
    }

    public void stop() {
        cleanInActivePersons();
        personProcesses.
                values()
                .forEach(pr->pr.setPersonActive(false));
    }
}
