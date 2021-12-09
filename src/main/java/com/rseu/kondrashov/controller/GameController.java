package com.rseu.kondrashov.controller;

import com.rseu.kondrashov.events.Listener;
import com.rseu.kondrashov.model.Game;
import com.rseu.kondrashov.model.Gibbd;
import com.rseu.kondrashov.model.Person;
import com.rseu.kondrashov.model.PersonProcess;
import com.rseu.kondrashov.utils.GameBackupManager;
import com.rseu.kondrashov.utils.GameCreator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.stream.Collectors;

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
                .values()
                .stream()
                .map(PersonProcess::getPerson)
                .forEach(p -> p.addListener(listener));
    }

    public void startProcess() {
        if (!isRunning) {
            game.startProcess();
        }
    }

    public PersonProcess addPerson(Listener viewListener) {
        game.cleanInActivePersons();
        Person person = GameCreator.createPerson(
                game
                        .getPersonProcesses()
                        .values()
                        .stream()
                        .map(pr -> pr.getPerson().getName())
                        .collect(Collectors.toList())
        );
        if (person != null) {
            person.addListener(viewListener);
            person.addListener(game);
            Gibbd gibbd = game.getGibbd();
            person.addListener(gibbd);
            person.setStateInstance(
                    gibbd.getStateStoragesMap().get(
                            gibbd
                                    .getStateChain()
                                    .getAvailableStates()
                                    .get(0)
                    ).tryToGetInstance());
            if (person.getStateInstance() == null) {
                System.out.println("no available initial stateInstance!");
                return null;
            }
            PersonProcess personProcess = new PersonProcess(person);
            game.addPerson(personProcess);
            return personProcess;
        } else {
            return null;
        }
    }

    public void stopGame() {
        game.stop();
        GameBackupManager.saveGame(game);
    }
}
