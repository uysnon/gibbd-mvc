package com.rseu.kondrashov.view;

import com.rseu.kondrashov.controller.GameController;
import com.rseu.kondrashov.events.Event;
import com.rseu.kondrashov.events.Listener;
import com.rseu.kondrashov.model.PersonProcess;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    private List<PersonView> personViews;
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
        this.personViews = new ArrayList<>();
        gameController.addListener(this);
        gameController
                .getGame()
                .getPersonProcesses()
                .stream()
                .map(PersonProcess::getPerson)
                .forEach(p ->
                        this.personViews.add(PersonView
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
                Color oldColor = g.getColor();
                g.setColor(Color.BLACK);
                g.drawString("ASFASFAF", 59, 59);
                backgroundView.paint(g);
                updateCoordinateForPlayersViews();
                personViews.forEach(v -> v.paint(g));
                g.setColor(oldColor);
            }
        };
        parentView.addNewPanel(contentPanel);
    }

    @Override
    public void draw() {
        parentView.repaint();
        contentPanel.repaint();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void handleEvent(Event event) {
        contentPanel.repaint();
    }

    private void updateCoordinateForPlayersViews() {
        coordinateSupplier.clear();
        for (PersonView personView : personViews) {
            Coordinate coordinate = coordinateSupplier
                    .stayOnFieldAndGetCoordinate(personView.getPerson());
            personView.setX(coordinate.getX());
            personView.setY(coordinate.getY());
        }
    }
}
