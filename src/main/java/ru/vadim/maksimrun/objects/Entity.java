package ru.vadim.maksimrun.objects;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public abstract class Entity extends JPanel {
    private Image image;

    public void paint(Graphics graphics) {
        graphics.drawImage(this.image, 0, 0, null);
    }
     protected void create(String imageId) {
        String path = "assets" + File.separator + "images" + File.separator + imageId + ".png";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String absolutePath = Objects.requireNonNull(classLoader.getResource(path)).getPath();
        this.image = new ImageIcon(absolutePath).getImage();
    }
}
