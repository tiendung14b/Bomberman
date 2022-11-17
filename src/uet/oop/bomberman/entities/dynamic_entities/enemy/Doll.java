package uet.oop.bomberman.entities.dynamic_entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamic_entities.Creature;
import uet.oop.bomberman.entities.static_entity.Object.Flame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.map;

public class Doll extends Creature {
    private final int time = 128;
    private int timeToChangeDir;
    private int animation;
    private int timeHide = 256;
    private int cntTimeHide = 0;
    private boolean isHide = false;
    private int timeExplode = 200;

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

    public int getTimeHide() {
        return timeHide;
    }

    public void setTimeHide(int timeHide) {
        this.timeHide = timeHide;
    }

    public int getCntTimeHide() {
        return cntTimeHide;
    }

    public void setCntTimeHide(int cntTimeHide) {
        this.cntTimeHide = cntTimeHide;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.timeToChangeDir = time;
        this.speed = 1;
        this.isMove = true;
        this.setAlive(true);
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
            if(Math.sqrt(Math.pow(this.getX() - map.getPlayer().getX(), 2) + Math.pow(this.getY() - map.getPlayer().getY(), 2)) < 3.0 * 32) {
                timeExplode--;
                if(timeExplode == 0) {
                    this.setImg(new Image("D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/Menu/transparent.png"));
                    this.setAlive(false);
                    map.getFlames().add(new Flame(this.getX() / 32 - 1, this.getY() / 32 - 1, null, "center"));
                    map.getFlames().add(new Flame(this.getX() / 32, this.getY() / 32 - 1, null, "center"));
                    map.getFlames().add(new Flame(this.getX() / 32 - 1, this.getY() / 32, null, "center"));
                    map.getFlames().add(new Flame(this.getX() / 32 + 1, this.getY() / 32, null, "center"));
                    map.getFlames().add(new Flame(this.getX() / 32, this.getY() / 32 + 1, null, "center"));
                    map.getFlames().add(new Flame(this.getX() / 32, this.getY() / 32, null, "center"));
                    map.getFlames().add(new Flame(this.getX() / 32 + 1, this.getY() / 32 - 1, null, "center"));
                    map.getFlames().add(new Flame(this.getX() / 32 - 1, this.getY() / 32 + 1, null, "center"));
                    map.getFlames().add(new Flame(this.getX() / 32 + 1, this.getY() / 32 + 1, null, "center"));
                }
            }
            if(cntTimeHide == 0) {
                this.isHide = false;
                cntTimeHide = timeHide;
            }
            if(cntTimeHide == timeHide) {
                isHide = true;
                this.setImg(new Image("D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/Menu/transparent.png"));
            }
            cntTimeHide--;
            move();
            handleAction();
        }
    }
}
