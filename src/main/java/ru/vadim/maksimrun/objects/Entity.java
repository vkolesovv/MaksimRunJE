package ru.vadim.maksimrun.objects;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public abstract class Entity extends JPanel {
    public Image image;
    public int CordX = 0;
    public int CordY = 0;

     protected void create(String imageId) {
         String path = "assets" + File.separator + "images" + File.separator + imageId + ".png";
         ClassLoader classLoader = ClassLoader.getSystemClassLoader();
         String absolutePath = Objects.requireNonNull(classLoader.getResource(path)).getPath();
         this.image = new ImageIcon(absolutePath).getImage();
     }

     public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics;

        graphics2d.drawImage(this.image, this.CordX, this.CordY, null);
     }
    public void move(int speed) {
        this.CordX -= speed;
    }
}
