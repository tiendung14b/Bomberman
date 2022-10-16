package uet.oop.bomberman.entities.dynamic_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.static_entity.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.graphics.Map.*;

public class Player extends Creature{
    private boolean putBomb;
    public Player(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setAlive(true);
        this.speed = 2;
        this.setFixRight(-10);
        this.setFixBottom(-4);
    }

    // hàm nhận xự kiện từ người chơi
    public void handleInputAction() {
        scene.setOnKeyPressed(keyEvent -> {
            this.setMove(true);
            switch (keyEvent.getCode()) {
                case W:
                case UP:
                    this.setDirection("up");
                    break;
                case DOWN:
                case S:
                    this.setDirection("down");
                    break;
                case A:
                case LEFT:
                    this.setDirection("left");
                    break;
                case D:
                case RIGHT:
                    this.setDirection("right");
                    break;
                case SPACE:
                    map.addBomb(new Bomb((this.getX() + 8) / 32, (this.getY() + 8) / 32, Sprite.bomb.getFxImage()));
                    break;
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            this.setMove(false);
            this.setIdAnimation(1);
            this.setDirection("idle");
        });
    }

    public boolean isPutBomb() {
        return putBomb;
    }

    public void setPutBomb(boolean putBomb) {
        this.putBomb = putBomb;
    }

    @Override
    public void update() {
        this.handleInputAction();
        this.handleAction();
    }
}
