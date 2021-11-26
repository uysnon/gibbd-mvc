package com.rseu.kondrashov.starter;

import com.rseu.kondrashov.controller.GameController;
import com.rseu.kondrashov.model.Game;
import com.rseu.kondrashov.utils.GameCreator;
import com.rseu.kondrashov.utils.GameParamsManager;
import com.rseu.kondrashov.view.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GibbdApp {
    private GibbdApp gibbdApp;
    private Supplier<Map<String, Supplier<View>>> viewSupplier;
    private ParentView parentView;

    public static void main(String[] args) {
        GibbdApp gibbdApp = new GibbdApp();
        gibbdApp.run();
    }

    private void run() {
        viewSupplier = createViewSupplier();
        parentView = createParentView();
        viewSupplier.get().get(MainMenuView.ID).get().draw();
    }

    private Supplier<Map<String, Supplier<View>>> createViewSupplier() {
        Map<String, Supplier<View>> viewsMap = new HashMap<>();
        viewsMap.put(MainMenuView.ID, this::createMainMenuView);
        viewsMap.put(GameView.ID, this::createGameView);
        viewsMap.put(ContinueGameView.ID, this::createContinueGameView);
        viewsMap.put(SettingsView.ID, this::createSettingsView);
        return () -> viewsMap;
    }

    private MainMenuView createMainMenuView() {
        return new MainMenuView(parentView, viewSupplier);
    }

    private ContinueGameView createContinueGameView() {
        // TODO
        return null;
    }

    private GameView createGameView() {
        Game game = GameCreator.createGame(GameParamsManager.getGameParams());
        GameController gameController = new GameController(game);
        return new GameView(
                parentView,
                viewSupplier,
                gameController);
    }

    private ParentView createParentView() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1000, 1000);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        return new ParentView(jFrame, jPanel);
    }

    private SettingsView createSettingsView() {
        // TODO
        return null;
    }
}
