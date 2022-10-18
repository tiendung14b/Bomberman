package uet.oop.bomberman.entities.static_entity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        this.setFixTop(0);
        this.setFixBottom(-2);
        this.setFixLeft(2);
        this.setFixRight(-4);
    }

    @Override
    public void update() {

    }

    @Override
    public String toString() {
        return "Wall{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
