package com.rseu.kondrashov.model;

import lombok.Data;

@Data
public class PersonProcess extends Thread {
    private final static int SLEEP_MS = 10;

    private Person person;

    @Override
    public void run() {
        super.run();
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
    }
}
