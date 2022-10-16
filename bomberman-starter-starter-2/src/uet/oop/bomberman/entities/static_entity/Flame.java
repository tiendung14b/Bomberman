package uet.oop.bomberman.entities.static_entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private static int power = 4;
    private int timeImpact;
    private String type;
    private int animation;
    public Flame(int xUnit, int yUnit, Image img, String type) {
        super(xUnit, yUnit, img);
        this.type = type;
        this.timeImpact = 36;
    }

    public static int getPower() {
        return power;
    }

    public void setPower(int power) {
        Flame.power = power;
    }

    public int getTimeImpact() {
        return timeImpact;
    }

    public void setTimeImpact(int timeImpact) {
        this.timeImpact = timeImpact;
    }

    public String toString() {
        return "Flame: " + '\n'
                + "type: " + this.type + ' '
                + "coordinate: " + this.getX() / 32 + " - " + this.getY() / 32;
    }

    private void updateFrameHorizon() {
        if (this.animation == 1 || this.animation == 3) {
            this.setImg(Sprite.explosion_horizontal.getFxImage());
        } else if (this.animation == 2) {
            this.setImg(Sprite.explosion_horizontal1.getFxImage());
        } else {
            this.setImg(Sprite.explosion_horizontal2.getFxImage());
        }
    }

    private void updateFrameVertical() {
        if (this.animation == 1 || this.animation == 3) {
            this.setImg(Sprite.explosion_vertical.getFxImage());
        } else if (this.animation == 2) {
            this.setImg(Sprite.explosion_vertical1.getFxImage());
        } else {
            this.setImg(Sprite.explosion_vertical2.getFxImage());
        }
    }

    private void updateFrameLeft() {
        if (this.animation == 1 || this.animation == 3) {
            this.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
        } else if (this.animation == 2) {
            this.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
        } else {
            this.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
        }
    }

    private void updateFrameRight() {
        if (this.animation == 1 || this.animation == 3) {
            this.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
        } else if (this.animation == 2) {
            this.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
        } else {
            this.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
        }
    }

    private void updateFrameUp() {
        if(this.animation == 1 || this.animation == 3) {
            this.setImg(Sprite.explosion_vertical_top_last.getFxImage());
        } else if(this.animation == 2) {
            this.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
        } else {
            this.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
        }
    }

    private void updateFrameDown() {
        if(this.animation == 1 || this.animation == 3) {
            this.setImg(Sprite.explosion_vertical_down_last.getFxImage());
        } else if(this.animation == 2) {
            this.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
        } else {
            this.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
        }
    }

    private void updateFrameCenter() {
        if(this.animation == 1 || this.animation == 3) {
            this.setImg(Sprite.bomb_exploded.getFxImage());
        } else if(this.animation == 2) {
            this.setImg(Sprite.bomb_exploded1.getFxImage());
        } else {
            this.setImg(Sprite.bomb_exploded2.getFxImage());
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
    }

    @Override
    public void update() {
        if(timeImpact % 8 == 0) {
            this.setAnimation(this.getAnimation() % 4 + 1);
        }
        timeImpact--;
        switch (this.type) {
            case "upLast":
                updateFrameUp();
                break;
            case "downLast":
                updateFrameDown();
                break;
            case "leftLast":
                updateFrameLeft();
                break;
            case "rightLast":
                updateFrameRight();
                break;
            case "up":
            case "down":
                updateFrameVertical();
                break;
            case "left":
            case "right":
                updateFrameHorizon();
                break;
            case "center":
                updateFrameCenter();
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
