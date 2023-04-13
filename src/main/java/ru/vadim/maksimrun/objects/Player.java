package ru.vadim.maksimrun.objects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public final class Player extends Entity implements ActionListener, Runnable {
    private int frame = 2;
    public int health = 100;
    public boolean canJump = true;
    public boolean isJump = false;
    public int speed = 4;
    public int jump_count = 13;
    public List<Entity> entities = new ArrayList<Entity>();
    Timer switchFrameT = new Timer(500, this);
    Timer default_event = new Timer(30, this);
    Thread thread = new Thread(this);
    public Player(int x, int y) {
        this.create("man/man22");
        switchFrameT.setActionCommand("Switch");
        default_event.setActionCommand("Default");

        switchFrameT.start();
        default_event.start();
        this.CordX = x;
        this.CordY = y;
        thread.start();

        addKeyListener(new KeyRegister());
        setFocusable(true);
    }

    public void switchFrame(int frame) {
        this.create("man/man" + frame + "2");
    }

    public void move() {
        for (Entity entity : entities) {
            entity.move(this.speed);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(e.getActionCommand(), "Switch") && !isJump) {
            if (frame < 3) {
                frame += 1;
            } else {
                frame = 2;
            }
            switchFrame(frame);
        } else if (Objects.equals(e.getActionCommand(), "Default")) {
            if (isJump) {
                jump();
            }
            if (frame != 4) {
                frame = 4;
                switchFrame(frame);
            }
        }
        super.paintComponent(getGraphics());
    }
    public void jump() {
        if(this.jump_count >= -13) {
            if (jump_count < 0) {
                this.CordY += Math.pow(jump_count, 2) / 3;
            } else {
                this.CordY -= Math.pow(jump_count, 2) / 3;
            }
            jump_count--;
        } else {
            jump_count = 13;
            this.isJump = false;
        }
        super.paintComponent(getGraphics());
    }

    private class KeyRegister extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            System.out.println("Pressed key");

            if (key == KeyEvent.VK_SPACE) {
                if (!isJump && canJump) {
                    isJump = true;
                    System.out.println("Is jump");
                }
            }
        }
    }
    @Override
    public void run() {
        while (true) {
            Random random = new Random();

            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
