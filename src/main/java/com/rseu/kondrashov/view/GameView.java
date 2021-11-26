package com.rseu.kondrashov.view;

import com.rseu.kondrashov.controller.GameController;
import com.rseu.kondrashov.events.Event;
import com.rseu.kondrashov.events.Listener;
import com.rseu.kondrashov.model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class GameView implements View, Listener {
    public final static String ID = "new_game";

    private GameController gameController;
    private ParentView parentView;
    private Supplier<Map<String, Supplier<View>>> viewsSupplier;
    private JPanel contentPanel;
    private PersonView personView;


    public GameView(
            ParentView parentView,
            Supplier<Map<String, Supplier<View>>> viewSupplier,
            GameController gameController) {
        this.parentView = parentView;
        this.viewsSupplier = viewSupplier;
        this.gameController = gameController;
        this.personView = PersonView
                .builder()
                .x(100)
                .y(100)
                .color(new Color(
                        ThreadLocalRandom.current().nextInt(0, 256),
                        ThreadLocalRandom.current().nextInt(0, 256),
                        ThreadLocalRandom.current().nextInt(0, 256)))
                .person(gameController.getGame().getPersonProcesses().get(0).getPerson())
                .build();
        createContentPane();
    }

    private void createContentPane() {
        contentPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                Color oldColor = g.getColor();
                g.setColor(Color.BLACK);
                g.drawString("ASFASFAF", 59, 59);
                personView.paint(g);
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
}
