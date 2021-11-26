package com.rseu.kondrashov.view;

import lombok.Data;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Data
public class MainMenuView implements View {
    public final static String ID = "main_menu";

    private Supplier<Map<String, Supplier<View>>> viewsSupplier;
    private ParentView parentView;
    private List<JButton> menuElementButtons;

    public MainMenuView(ParentView parentView, Supplier<Map<String, Supplier<View>>> viewSupplier) {
        this.parentView = parentView;
        this.viewsSupplier = viewSupplier;
        generateMenuButtons().forEach(
                button -> parentView.getViewPanel().add(button)
        );
    }

    @Override
    public void draw() {
        parentView.repaint();
    }

    @Override
    public String getId() {
        return ID;
    }

    private List<JButton> generateMenuButtons() {
        List<JButton> buttons = new ArrayList<>();
        if (isContinueGameButtonNeed()) {
            buttons.add(generateContinueGameButton());
        }
        buttons.add(generateStartGameButton());
        buttons.add(generateSettingsGameButton());
        return buttons;
    }

    private JButton generateStartGameButton() {
        JButton jButton = new JButton();
        jButton.setText("Запустить новый процесс");
        jButton.addActionListener(e -> {
            parentView.clear();
            viewsSupplier.get().get(GameView.ID).get().draw();
        });
        return jButton;
    }

    private JButton generateContinueGameButton() {
        JButton jButton = new JButton();
        jButton.setText("Продолжить процесс");
        jButton.addActionListener(e -> {
            parentView.clear();
            viewsSupplier.get().get(ContinueGameView.ID).get().draw();
        });
        return jButton;
    }

    private JButton generateSettingsGameButton() {
        JButton jButton = new JButton();
        jButton.setText("Настройки");
        jButton.addActionListener(e -> {
            parentView.clear();
            viewsSupplier.get().get(SettingsView.ID).get().draw();
        });
        return jButton;
    }

    private boolean isContinueGameButtonNeed() {
        // TODO
        return false;
    }
}
