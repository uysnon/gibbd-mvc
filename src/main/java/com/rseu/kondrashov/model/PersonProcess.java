package com.rseu.kondrashov.model;

import lombok.Data;

@Data
public class PersonProcess extends Thread {
    private final static int SLEEP_MS = 4;

    private boolean isPersonActive;
    private Person person;

    public PersonProcess(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        super.run();
        isPersonActive = true;
        while (person.getStateInstance() != null) {
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
