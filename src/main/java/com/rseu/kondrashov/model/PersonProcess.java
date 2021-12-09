package com.rseu.kondrashov.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonProcess extends Thread implements Serializable {
    private final static int SLEEP_MS = 4;
    private static final long serialVersionUID = 1L;

    private boolean isPersonActive;
    private Person person;

    public PersonProcess(Person person) {
        this.person = person;
    }

    public void setPersonActive(boolean personActive) {
        isPersonActive = personActive;
    }

    @Override
    public void run() {
        super.run();
        isPersonActive = true;
        while (person.getStateInstance() != null
                || !isPersonActive) {
            try {
                Thread.sleep(SLEEP_MS);
                if (person.getStateInstance().isCompleted()) {
                    person.sendReadyForNextState();
                } else {
                    person.getStateInstance().makeWork(SLEEP_MS);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isPersonActive = false;
    }
}
