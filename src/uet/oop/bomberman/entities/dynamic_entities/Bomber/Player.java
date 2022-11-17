package uet.oop.bomberman.entities.dynamic_entities.Bomber;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamic_entities.Creature;
import uet.oop.bomberman.entities.static_entity.Object.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Player extends Creature {
    private boolean putBomb;
    private int numberOfPlay;
    private int score;

    public Player(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
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
                    this.setDirection("up");
                    break;
                case S:
                    this.setDirection("down");
                    break;
                case A:
                    this.setDirection("left");
                    break;
                case D:
                    this.setDirection("right");
                    break;
                case B:
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isPutBomb() {
        return putBomb;
    }

    public void setPutBomb(boolean putBomb) {
        this.putBomb = putBomb;
    }

    public int getNumberOfPlay() {
        return numberOfPlay;
    }

    public void setNumberOfPlay(int numberOfPlay) {
        this.numberOfPlay = numberOfPlay;
    }

//    @Override
//    public void render(GraphicsContext gc) {
//        gc.drawImage(this.getImg(), screenX, screenY);
//    }

    @Override
    public void update() {
        if(this.isAlive()) {
            this.handleInputAction();
            this.handleAction();
        }
        if (!this.isAlive()) {
            this.renderDeathAnimation();
            this.timeDeath--;
            if(timeDeath == 0) {
                isPause = true;
            }
        }
    }
}
