package com.rseu.kondrashov.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Game {
    private List<PersonProcess> personProcesses;
    private Gibbd gibbd;

    public void startProcess() {
        personProcesses.forEach(Thread::start);
    }
}
