package uet.oop.bomberman.entities.dynamic_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloon extends Creature{
    private final int time = 128;
    private final int timeRemove = 32;
    private int timeToChangeDir;
    private int animation;
    private int removeAnimation;
    private int oldDir = -1;
    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.timeToChangeDir = time;
        this.speed = 1;
        this.isMove = true;
        this.setAlive(true);
        this.setFixLeft(4);
        this.setFixRight(-4);
        this.setFixTop(2);
        this.setFixBottom(-4);
    }

    public int getTimeToChangeDir() {
        return timeToChangeDir;
    }

    public void setTimeToChangeDir(int timeToChangeDir) {
        this.timeToChangeDir = timeToChangeDir;
    }

    public int getOldDir() {
        return oldDir;
    }

    public void setOldDir(int oldDir) {
        this.oldDir = oldDir;
    }

    public void move() {
        if(!this.isAlive()) {
            return;
        }
        if(timeToChangeDir == 0) {
            animation = new Random().nextInt() % 4;
            timeToChangeDir = time;
        }
        timeToChangeDir--;
        switch (animation) {
            case 0:
                this.setDirection("up");
                break;
            case 1:
                this.setDirection("down");
                break;
            case 2:
                this.setDirection("left");
                break;
            case 3:
                this.setDirection("right");
                break;
        }
    }

    @Override
    public void update() {
        move();
        if(!this.isAlive()) {
            this.timeDeath--;
        }
        handleAction();
    }

    @Override
    public String toString() {
        return "Balloon{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
