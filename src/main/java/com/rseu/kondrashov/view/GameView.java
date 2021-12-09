package com.rseu.kondrashov.view;

import com.rseu.kondrashov.controller.GameController;
import com.rseu.kondrashov.events.Event;
import com.rseu.kondrashov.events.EventTags;
import com.rseu.kondrashov.events.Listener;
import com.rseu.kondrashov.events.UpdateEventData;
import com.rseu.kondrashov.model.Person;
import com.rseu.kondrashov.model.PersonProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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
    private JButton mainMenuButton;
    private JButton addPersonButton;
    private JPanel parentContentPanel;
    private JPanel buttonsPanel;


    public GameView(
            ParentView parentView,
            Supplier<Map<String, Supplier<View>>> viewSupplier,
            GameController gameController) {
        this.coordinateSupplier = new CoordinateSupplier();
        this.parentView = parentView;
        this.viewsSupplier = viewSupplier;
        this.gameController = gameController;
        this.backgroundView = new BackgroundView();


        parentContentPanel = new JPanel();
        parentContentPanel.setLayout(new BorderLayout());
        parentView.addNewPanel(parentContentPanel);

        initPersonViews();
        createContentPane();
        initButtons();
    }

    private void initButtons() {
        this.addPersonButton = new JButton("Добавить");
        addPersonButton.addActionListener(e -> {
            PersonProcess personProcess = gameController.addPerson(GameView.this);
            if (personProcess != null) {
                Person person = personProcess.getPerson();
                personViews.put(
                        person.getId(),
                        createPersonViewFromPerson(person)
                );
            }
        });
        this.mainMenuButton = new JButton("Главное меню");
        buttonsPanel = new JPanel();
        buttonsPanel.add(addPersonButton);
        buttonsPanel.add(mainMenuButton);
        parentContentPanel.add(buttonsPanel, BorderLayout.PAGE_END);
    }

    private void initPersonViews() {
        this.personViews = new HashMap<>();
        gameController.addListener(this);
        gameController
                .getGame()
                .getPersonProcesses()
                .values()
                .stream()
                .map(PersonProcess::getPerson)
                .forEach(p ->
                        this.personViews.put(
                                p.getId(),
                                createPersonViewFromPerson(p)
                        ));
    }

    private PersonView createPersonViewFromPerson(Person person) {
        return
                PersonView
                        .builder()
                        .color(new Color(
                                ThreadLocalRandom.current().nextInt(0, 256),
                                ThreadLocalRandom.current().nextInt(0, 256),
                                ThreadLocalRandom.current().nextInt(0, 256)))
                        .person(person)
                        .build();
    }

    private void createContentPane() {
        contentPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                synchronized (GameView.this) {
                    Color oldColor = g.getColor();
                    g.setColor(Color.BLACK);
                    backgroundView.paint(g);
                    updateCoordinateForPlayersViews();
                    personViews.values().forEach(v -> v.paint(g));
                    g.setColor(oldColor);
                    addPersonButton.repaint();
                    mainMenuButton.repaint();
                }
            }
        };
        contentPanel.setSize(new Dimension(1000, 500));
        parentContentPanel.add(contentPanel);
    }

    @Override
    public void draw() {
        if (!gameController.isRunning()) {
            gameController.startProcess();
        }
        parentView.repaint();
        parentContentPanel.repaint();
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
            if (personView.getPerson().getStateInstance() != null) {
                Coordinate coordinate = coordinateSupplier
                        .stayOnFieldAndGetCoordinate(personView.getPerson());
                if (coordinate != null) {
                    personView.setX(coordinate.getX());
                    personView.setY(coordinate.getY());
                }
            }
        }
    }
}
