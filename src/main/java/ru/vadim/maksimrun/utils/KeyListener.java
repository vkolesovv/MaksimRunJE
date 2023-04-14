package ru.vadim.maksimrun.utils;

import java.awt.event.*;
import ru.vadim.maksimrun.objects.Player;

public class KeyListener extends KeyAdapter {
    private final Player player;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        System.out.println("Pressed key");

        if (key == KeyEvent.VK_SPACE) {
            if (!player.isJump && player.canJump) {
                player.isJump = true;
                System.out.println("Is jump");
            }
        }
    }
    public KeyListener(Player player) {
        this.player = player;
    }
}
