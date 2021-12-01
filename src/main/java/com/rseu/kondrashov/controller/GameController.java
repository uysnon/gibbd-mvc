package com.rseu.kondrashov.controller;

import com.rseu.kondrashov.events.Listener;
import com.rseu.kondrashov.model.Game;
import com.rseu.kondrashov.model.PersonProcess;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameController {
    private Game game;
    private boolean isRunning;

    public GameController(Game game) {
        this.game = game;
        this.isRunning = false;
    }

    public void addListener(Listener listener) {
        game.getPersonProcesses()
                .stream()
                .map(PersonProcess::getPerson)
                .forEach(p -> p.addListener(listener));
    }

    public void startProcess() {
        if (!isRunning) {
            game.startProcess();
        }
    }
}
