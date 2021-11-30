package com.rseu.kondrashov.view;

import com.rseu.kondrashov.model.Person;
import com.rseu.kondrashov.model.StateInstance;
import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Data
@Builder
public class PersonView implements SubView {
    private int x;
    private int y;
    private Color color;
    private Person person;

    @Override
    public void paint(Graphics graphics) {
        Color oldColor = graphics.getColor();
        graphics.setColor(color);
        drawSmile(x, y, 0.2, graphics);
        drawBattery(x, y, graphics);
        graphics.drawString(
                person.getName(),
                x + 25,
                y + 20
        );
        graphics.setColor(oldColor);
    }

    private void drawSmile(int x, int y, double k, Graphics graphics) {
        int x0 = 10;
        x0 = (int) Math.round(x0 * k);
        int y0 = 10;
        y0 = (int) Math.round(y0 * k);
        ((Graphics2D) graphics).setStroke(new BasicStroke(2));
        graphics.drawOval(x + x0, y + y0, transformValue(100, k), transformValue(100, k));
        graphics.fillOval(transformValue(20, x, x0, k), transformValue(30, y, y0, k), transformValue(20, k), transformValue(20, k));
        graphics.fillOval(transformValue(60, x, x0, k), transformValue(30, y, y0, k), transformValue(20, k), transformValue(20, k));
        graphics.drawLine(transformValue(30, x, x0, k), transformValue(70, y, y0, k), transformValue(35, x, x0, k), transformValue(75, y, y0, k));
        graphics.drawLine(transformValue(35, x, x0, k), transformValue(75, y, y0, k), transformValue(63, x, x0, k), transformValue(75, y, y0, k));
        graphics.drawLine(transformValue(63, x, x0, k), transformValue(75, y, y0, k), transformValue(68, x, x0, k), transformValue(70, y, y0, k));
    }

    private void drawBattery(int x, int y, Graphics graphics) {
        StateInstance stateInstance = person.getStateInstance();
        double totalWork = stateInstance.getTotalWork();
        double completedWork = stateInstance.getCompletedWork();
        double percentCompleted = totalWork > 0 ? completedWork / totalWork : 1;
        percentCompleted = percentCompleted > 1 ? 1 : percentCompleted;
        graphics.drawRect(x, y + 30, 30, 10);
        graphics.fillRect(x, y + 30, transformValue(30, percentCompleted), 10);
    }

    private int transformValue(double variable, int xy, int xy0, double k) {
        return (int) Math.round(variable * k + xy + xy0);
    }

    private int transformValue(double variable, double k) {
        return (int) Math.round(variable * k);
    }
}
