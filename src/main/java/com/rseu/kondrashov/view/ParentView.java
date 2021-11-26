package com.rseu.kondrashov.view;

import lombok.Data;

import javax.swing.*;

@Data
public class ParentView {
    private JFrame window;
    private JPanel viewPanel;

    public ParentView(JFrame window, JPanel viewPanel) {
        this.window = window;
        this.viewPanel = viewPanel;
        window.add(viewPanel);
    }

    public void clear() {
        viewPanel.removeAll();
        window.revalidate();
        window.repaint();
    }

    public void addNewPanel(JPanel panel){
        window.getContentPane().removeAll();
        viewPanel = panel;
        window.add(viewPanel);
    }

    public void repaint() {
        window.revalidate();
        window.transferFocus();
    }
}
