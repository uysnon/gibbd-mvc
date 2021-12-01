package com.rseu.kondrashov.view;

import com.rseu.kondrashov.controller.GameController;
import com.rseu.kondrashov.events.Event;
import com.rseu.kondrashov.events.EventTags;
import com.rseu.kondrashov.events.Listener;
import com.rseu.kondrashov.events.UpdateEventData;
import com.rseu.kondrashov.model.Person;
import com.rseu.kondrashov.model.PersonProcess;
import com.rseu.kondrashov.model.StateInstance;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class GameView implements View, Listener {
    public final static String ID = "new_game";

    private CoordinateSupplier coordinateSupplier;
    private GameController gameController;
    private ParentView parentView;
    private Supplier<Map<String, Supplier<View>>> viewsSupplier;
    private JPanel contentPanel;
    private Map<String, PersonView> personViews;
    private BackgroundView backgroundView;


    public GameView(
            ParentView parentView,
            Supplier<Map<String, Supplier<View>>> viewSupplier,
            GameController gameController) {
        this.coordinateSupplier = new CoordinateSupplier();
        this.parentView = parentView;
        this.viewsSupplier = viewSupplier;
        this.gameController = gameController;
        this.backgroundView = new BackgroundView();
        initPersonViews();
        createContentPane();
    }

    private void initPersonViews() {
        this.personViews = new HashMap<>();
        gameController.addListener(this);
        gameController
                .getGame()
                .getPersonProcesses()
                .stream()
                .map(PersonProcess::getPerson)
                .forEach(p ->
                        this.personViews.put(
                                p.getId(),
                                PersonView
                                        .builder()
                                        .color(new Color(
                                                ThreadLocalRandom.current().nextInt(0, 256),
                                                ThreadLocalRandom.current().nextInt(0, 256),
                                                ThreadLocalRandom.current().nextInt(0, 256)))
                                        .person(p)
                                        .build()
                        ));
    }

    private void createContentPane() {
        contentPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                synchronized (GameView.this) {
                    Color oldColor = g.getColor();
                    g.setColor(Color.BLACK);
                    g.drawString("ASFASFAF", 59, 59);
                    backgroundView.paint(g);
                    updateCoordinateForPlayersViews();
                    personViews.values().forEach(v -> v.paint(g));
                    g.setColor(oldColor);
                }
            }
        };
        parentView.addNewPanel(contentPanel);
    }

    @Override
    public void draw() {
        if (!gameController.isRunning()) {
            gameController.startProcess();
        }
        parentView.repaint();
        contentPanel.repaint();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public synchronized void handleEvent(Event event) {
        handlePersonFinished(event);
        contentPanel.repaint();
    }

    private void handlePersonFinished(Event event) {
        if (EventTags.NEW_STATE.equals(event.getTag())
                && ((UpdateEventData) event.getData()).getNewValue() == null) {
            personViews.remove(((Person) event.getSender()).getId());
        }
    }

    private void updateCoordinateForPlayersViews() {
        coordinateSupplier.clear();
        for (PersonView personView : personViews.values()) {
            Coordinate coordinate = coordinateSupplier
                    .stayOnFieldAndGetCoordinate(personView.getPerson());
            personView.setX(coordinate.getX());
            personView.setY(coordinate.getY());
        }
    }
}
