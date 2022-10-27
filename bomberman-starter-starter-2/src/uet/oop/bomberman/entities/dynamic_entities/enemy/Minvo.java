package uet.oop.bomberman.entities.dynamic_entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamic_entities.Creature;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.*;

public class Minvo extends Creature {
    private final int time = 128;
    private int timeToChangeDir;
    private int animation;

    public int getTime() {
        return time;
    }

    public int getTimeToChangeDir() {
        return timeToChangeDir;
    }

    public void setTimeToChangeDir(int timeToChangeDir) {
        this.timeToChangeDir = timeToChangeDir;
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
    }

    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.timeToChangeDir = time;
        this.speed = 2;
        this.isMove = true;
        this.setAlive(true);
        this.setDirection("right");
    }

    public void move() {
        if (!this.isAlive()) {
            return;
        }
        if (timeToChangeDir == 0) {
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
        if (!this.isAlive()) {
            this.renderDeathAnimation();
            this.timeDeath--;
        } else {
            move();
            handleAction();
        }
    }
}
