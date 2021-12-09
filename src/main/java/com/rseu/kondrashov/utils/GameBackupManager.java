package com.rseu.kondrashov.utils;

import com.rseu.kondrashov.model.Game;
import com.rseu.kondrashov.model.Gibbd;
import com.rseu.kondrashov.model.PersonProcess;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.util.ArrayList;

@UtilityClass
public class GameBackupManager {
    String backupFileName = "gameBackup.dat";

    public boolean isBackupExists() {
        return new File(backupFileName).exists();
    }

    public void saveGame(Game game) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(backupFileName));
            oos.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game getGame() {
        Game gameOuter = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backupFileName));
            Game game = (Game)ois.readObject();
            Gibbd gibbd = game.getGibbd();
            game
                    .getPersonProcesses()
                    .values()
                    .stream()
                    .map(PersonProcess::getPerson)
                    .forEach(p->{
                        p.setListeners(new ArrayList<>());
                        p.addListener(gibbd);
                        p.addListener(game);
                    });
            gameOuter = game;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameOuter;
    }
}
