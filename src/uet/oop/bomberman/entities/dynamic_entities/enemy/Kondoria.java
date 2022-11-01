package uet.oop.bomberman.entities.dynamic_entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamic_entities.Creature;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.*;

public class Kondoria extends Creature {
    private final int time = 128;
    private int timeToChangeDir;
    private int animation;
    String preventDir = null;

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.timeToChangeDir = time;
        this.speed = 1;
        this.isMove = true;
        this.setAlive(true);
        this.setDirection("right");
    }

    public int getTimeToChangeDir() {
        return timeToChangeDir;
    }

    public void setTimeToChangeDir(int timeToChangeDir) {
        this.timeToChangeDir = timeToChangeDir;
    }

    public void setPreventDir() {
        if (this.getDirection().equals("left")) {
            for(int i = 0; i < map.getBombs().size(); i++) {
                if(Math.round(this.getY() / 32.0) == map.getBombs().get(i).getY() / 32 && this.getX() / 32 > map.getBombs().get(i).getX() / 32) {
                    this.setDirection("right");
                }
            }
        } else if (this.getDirection().equals("right")) {
            for(int i = 0; i < map.getBombs().size(); i++) {
                if(Math.round(this.getY() / 32.0) == map.getBombs().get(i).getY() / 32 && this.getX() / 32 < map.getBombs().get(i).getX() / 32) {
                    this.setDirection("left");
                    break;
                }
            }
        } else if (this.getDirection().equals("up")) {
            for(int i = 0; i < map.getBombs().size(); i++) {
                if(Math.round(this.getX() / 32.0) == map.getBombs().get(i).getX() / 32 && this.getY() / 32 > map.getBombs().get(i).getY() / 32) {
                    this.setDirection("down");
                    break;
                }
            }
        } else {
            for(int i = 0; i < map.getBombs().size(); i++) {
                if(Math.round(this.getX() / 32.0) == map.getBombs().get(i).getX() / 32 && this.getY() / 32 < map.getBombs().get(i).getY() / 32) {
                    this.setDirection("up");
                    break;
                }
            }
        }
    }

    public void move() {
        if (!this.isAlive()) {
            return;
        }
        this.setSpeed(1);
        for(int i = 0; i < map.getBombs().size(); i++) {
            if(Math.sqrt(Math.pow(this.getX() - map.getBombs().get(i).getX(), 2) + Math.pow(this.getY() - map.getBombs().get(i).getY(), 2)) < 3.0 * 32) {
                this.setSpeed(2);
            }
        }
        if (timeToChangeDir == 0) {
//            System.out.println("checked");
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
        this.setPreventDir();
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
