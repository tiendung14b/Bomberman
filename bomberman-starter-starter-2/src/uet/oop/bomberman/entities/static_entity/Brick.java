package uet.oop.bomberman.entities.static_entity;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Brick extends Entity {
    protected int animation;
    public Brick(int x, int y, Image image) {
        super(x, y, image);
        this.timeRemove = 48;
        this.setFixTop(0);
        this.setFixBottom(-2);
        this.setFixLeft(2);
        this.setFixRight(-4);
    }

    void removeAnimation() {
        if (animation == 1) {
            this.setImg(Sprite.brick_exploded.getFxImage());
        } else if (animation == 2) {
            this.setImg(Sprite.brick_exploded1.getFxImage());
        } else {
            this.setImg(Sprite.brick_exploded2.getFxImage());
        }
    }

    @Override
    public void update() {
        if(!this.isBreak()) {
            return;
        }
        if(timeRemove % 16 == 0) {
            this.animation++;
        }
        this.timeRemove--;
        removeAnimation();
    }

    @Override
    public String toString() {
        return "Brick{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
