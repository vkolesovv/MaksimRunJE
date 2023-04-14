package ru.vadim.maksimrun.objects;

import ru.vadim.maksimrun.utils.KeyListener;

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public final class Player extends Entity implements ActionListener, Runnable {
    private int frame = 2;
    public boolean canJump = true;
    boolean threading = true;
    public boolean isJump = false;
    public int jump_count = 20;
    public List<Entity> entities = new ArrayList<Entity>();
    Timer switchFrameT = new Timer(500, this);
    Timer default_event = new Timer(30, this);
    Timer movement = new Timer(20, this);
    Thread thread = new Thread(this);
    public Player(int x, int y) {
        this.create("man/man22");
        switchFrameT.setActionCommand("Switch");
        default_event.setActionCommand("Default");
        movement.setActionCommand("Move");

        movement.start();
        switchFrameT.start();
        default_event.start();
        this.CordX = x;
        this.CordY = y;
        thread.start();

        addKeyListener(new KeyListener(this));
        setFocusable(true);
    }

    public void switchFrame(int frame) {
        this.create("man/man" + frame + "2");
    }

    public void move() {
        for (Entity entity : entities) {
            entity.move(6);
        }
    }

    public void drawAll() {
        Graphics graphics = getGraphics();
        paintComponent(getGraphics());
        Graphics2D graphics2D = (Graphics2D) graphics;

        for (Entity entity : entities) {
            graphics2D.drawImage(entity.image, entity.CordX, entity.CordY, null);
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
        } else if (Objects.equals(e.getActionCommand(), "Move")) {
            move();
            drawAll();
        }
    }
    public void jump() {
        if(this.jump_count >= -20) {
            if (jump_count < 0) {
                this.CordY += Math.pow(jump_count, 2) / 10;
            } else {
                this.CordY -= Math.pow(jump_count, 2) / 10;
            }
            jump_count--;
        } else {
            jump_count = 20;
            this.isJump = false;
        }
    }
    @Override
    public void run() {
        while (threading) {
            Random random = new Random();

            if (random.nextDouble() * 100 < 3) {
                entities.add(new Poop());
            }
            if (random.nextDouble() * 1000 <= 2) {
                entities.add(new Peas());
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
