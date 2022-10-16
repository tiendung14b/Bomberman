package uet.oop.bomberman.entities.static_entity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int animation;
    private int time;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.time = 128;
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private Image getImage() {
        if (animation == 1 || animation == 3) {
            return Sprite.bomb.getFxImage();
        } else if (animation == 2) {
            return Sprite.bomb_1.getFxImage();
        } else if (animation == 4) {
            return Sprite.bomb_2.getFxImage();
        }
        return Sprite.bomb.getFxImage();
    }

    @Override
    public void update() {
        if(time % 8 == 0) {
            this.animation = this.animation % 4 + 1;
        }
        this.time--;
        this.setImg(getImage());
    }
}
