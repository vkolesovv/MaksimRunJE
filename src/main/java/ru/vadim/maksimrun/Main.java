package ru.vadim.maksimrun;

import ru.vadim.maksimrun.objects.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = init("Maksim Run Java Edition", 1000, 600);
        Player player = new Player(100, 200);

        jFrame.add(player);
        // End
        jFrame.setVisible(true);


    }
    public static JFrame init(String title, int width, int height) {
        // Initialization
        JFrame jFrame = new JFrame(title);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width, height);

        return jFrame;
    }
}