package com.rseu.kondrashov.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackgroundView implements SubView {
    private final static String IMAGE_PATH = "map.bmp";
    private Image backgroundImage;

    public BackgroundView(){
        try {
            backgroundImage = ImageIO.read(new File(getClass().getClassLoader().getResource(IMAGE_PATH).getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, null);
    }
}
