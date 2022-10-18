package uet.oop.bomberman.entities.dynamic_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.entities.static_entity.*;
import uet.oop.bomberman.entities.static_entity.Item.Item;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Map.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Creature extends Entity {
    //------------------------------
    // thuộc tính của creature
    //-------------------------------
    protected boolean isAlive;
    protected boolean isMove;
    protected int idAnimation; // set animation
    protected String direction; // hướng đi của object
    protected int speed;
    protected int timeToRefreshFrame = 0; // thời gian để làm mới animation
    protected int timeDeath = 128;
    protected int animationDeath = 0;
    //------------------------------
    // method
    //-------------------------------

    public Creature(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isMove() {
        return isMove;
    }

    public void setMove(boolean move) {
        isMove = move;
    }

    public int getIdAnimation() {
        return idAnimation;
    }

    public void setIdAnimation(int idAnimation) {
        this.idAnimation = idAnimation;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTimeDeath() {
        return timeDeath;
    }

    public void setTimeDeath(int timeDeath) {
        this.timeDeath = timeDeath;
    }

    public int getTimeToRefreshFrame() {
        return timeToRefreshFrame;
    }

    // time delay for animation
    public void setTimeToRefreshFrame(int timeToRefreshFrame) {
        if (timeToRefreshFrame >= 32) {
            this.timeToRefreshFrame = 0;
        } else {
            this.timeToRefreshFrame = timeToRefreshFrame;
        }
    }

    //------------------------------
    // di chuyen + render
    //-------------------------------
    public void handleAction() {
        if(!this.isMove()) { return; }

        if(this.getTimeToRefreshFrame() == 0) {
            this.setIdAnimation(this.getIdAnimation() % 4 + 1);
            if(this instanceof Player) {
                if(this.getIdAnimation() == 1) {
                    new Sound("bomberman-starter-starter-2/res/sound/walk_1.wav").play();
                } else if (this.getIdAnimation() == 3) {
                    new Sound("bomberman-starter-starter-2/res/sound/walk_2.wav").play();
                }
            }
        }

        int _x = 0, _y = 0;
        switch (this.getDirection()) {
            case "up":
                upStep();
                _y =  -1 * this.getSpeed();
                break;
            case "down":
                downStep();
                _y = this.getSpeed();
                break;
            case "left":
                leftStep();
                _x = -1 * this.getSpeed();
                break;
            case "right":
                rightStep();
                _x = this.getSpeed();
                break;
        }
        this.setTimeToRefreshFrame(this.timeToRefreshFrame + this.getSpeed());
        this.setX(this.getX() + _x);
        this.setY(this.getY() + _y);
        this.handleCollision(_x, _y);
    }

    public void upStep() {
        Image idle,action1, action2;
        if(this instanceof Player) {
            idle = Sprite.player_up.getFxImage();
            action1 = Sprite.player_up_1.getFxImage();
            action2 = Sprite.player_up_2.getFxImage();
        } else {
            idle = Sprite.balloom_left1.getFxImage();
            action1 = Sprite.balloom_left2.getFxImage();
            action2 = Sprite.balloom_left3.getFxImage();
        }
        switch (this.getIdAnimation()) {
            case 1:
            case 3:
                this.setImg(idle);
                break;
            case 2:
                this.setImg(action1);
                break;
            case 4:
                this.setImg(action2);
                break;
        }
    }

    public void leftStep() {
        Image idle,action1, action2;
        if(this instanceof Player) {
            idle = Sprite.player_left.getFxImage();
            action1 = Sprite.player_left_1.getFxImage();
            action2 = Sprite.player_left_2.getFxImage();
        } else {
            idle = Sprite.balloom_left1.getFxImage();
            action1 = Sprite.balloom_left2.getFxImage();
            action2 = Sprite.balloom_left3.getFxImage();
        }
        switch (this.getIdAnimation()) {
            case 1:
            case 3:
                this.setImg(idle);
                break;
            case 2:
                this.setImg(action1);
                break;
            case 4:
                this.setImg(action2);
                break;
        }
    }

    public void rightStep() {
        Image idle,action1, action2;
        if(this instanceof Player) {
            idle = Sprite.player_right.getFxImage();
            action1 = Sprite.player_right_1.getFxImage();
            action2 = Sprite.player_right_2.getFxImage();
        } else {
            idle = Sprite.balloom_right1.getFxImage();
            action1 = Sprite.balloom_right2.getFxImage();
            action2 = Sprite.balloom_right3.getFxImage();
        }
        switch (this.getIdAnimation()) {
            case 1:
            case 3:
                this.setImg(idle);
                break;
            case 2:
                this.setImg(action1);
                break;
            case 4:
                this.setImg(action2);
                break;
        }
    }

    public void downStep() {
        Image idle,action1, action2;
        if(this instanceof Player) {
            idle = Sprite.player_down.getFxImage();
            action1 = Sprite.player_down_1.getFxImage();
            action2 = Sprite.player_down_2.getFxImage();
        } else {
            idle = Sprite.balloom_right1.getFxImage();
            action1 = Sprite.balloom_right2.getFxImage();
            action2 = Sprite.balloom_right3.getFxImage();
        }
        switch (this.getIdAnimation()) {
            case 1:
            case 3:
                this.setImg(idle);
                break;
            case 2:
                this.setImg(action1);
                break;
            case 4:
                this.setImg(action2);
                break;
        }
    }

    public void renderDeathAnimation() {
        this.setTimeToRefreshFrame(this.getTimeToRefreshFrame() + 2);
        if(timeToRefreshFrame == 0) {
            this.animationDeath++;
        }
        Image action1,action2, action3, action4;
        if(this instanceof Player) {
            action1 = Sprite.player_dead1.getFxImage();
            action2 = Sprite.player_dead2.getFxImage();
            action3 = Sprite.player_dead3.getFxImage();
            action4 = Sprite.player_dead3.getFxImage();
        } else if (this instanceof Balloon){
            action1 =  Sprite.balloom_dead.getFxImage();
            action2 = Sprite.mob_dead1.getFxImage();
            action3 = Sprite.mob_dead2.getFxImage();
            action4 = Sprite.mob_dead3.getFxImage();
        } else {
            return;
        }
        switch (this.animationDeath) {
            case 1:
                this.setImg(action1);
                break;
            case 3:
                this.setImg(action2);
                break;
            case 2:
                this.setImg(action3);
                break;
            case 4:
                this.setImg(action4);
                break;
        }
    }

    //------------------------------
    // xu ly va cham
    //-------------------------------
    public boolean checkCollision(Entity entity) {
        // the coordinates of the corners - sides of the object
        int left_a = this.x + this.getFixLeft();
        int right_a = this.x + 32 + this.getFixRight();
        int top_a = this.y + this.getFixTop();
        int bottom_a = this.y + 32 + this.getFixBottom();
        // the coordinates of the corners - sides of the entity
        int left_b = entity.getX() + entity.getFixLeft();
//        int left_b = entity.getX() + 2;
        int right_b = entity.getX() + 32 + entity.getFixRight();
//        int right_b = entity.getX() + 28;
        int top_b = entity.getY() + entity.getFixTop();
//        int top_b = entity.getY();
        int bottom_b = entity.getY() + 32 + entity.getFixBottom();
//        int bottom_b = entity.getY() + 30;

        if (left_a > left_b && left_a < right_b) {
            if (top_a > top_b && top_a < bottom_b) {
                return true;
            }
        }

        if (left_a > left_b && left_a < right_b) {
            if (bottom_a > top_b && bottom_a < bottom_b) {
                return true;
            }
        }

        if (right_a > left_b && right_a < right_b) {
            if (top_a > top_b && top_a < bottom_b) {
                return true;
            }
        }

        if (right_a > left_b && right_a < right_b) {
            if (bottom_a > top_b && bottom_a < bottom_b) {
                return true;
            }
        }

        if (left_b > left_a && left_b < right_a) {
            if (top_b > top_a && top_b < bottom_a) {
                return true;
            }
        }

        if (left_b > left_a && left_b < right_a) {
            if (bottom_b > top_a && bottom_b < bottom_a) {
                return true;
            }
        }

        if (right_b > left_a && right_b < right_a) {
            if (top_b > top_a && top_b < bottom_a) {
                return true;
            }
        }

        if (right_b > left_a && right_b < right_a) {
            if (bottom_b > top_a && bottom_b < bottom_a) {
                return true;
            }
        }

        return top_a == top_b && right_a == right_b && bottom_a == bottom_b;
    }

    public void handleCollision(int _x, int _y) {
        boolean is_col = false;
        // kiểm tra xem có va chạm với flame không
        for (Flame flame : map.getFlames()) {
            if(this.checkCollision(flame)) {
                this.setAlive(false);
            }
        }
        // kiểm tra xem người có va chạm với quái không
        if (this instanceof Player) {
           for(Creature creature : map.getEnemy()) {
               if(this.checkCollision(creature)) {
                   this.setAlive(false);
               }
           }
        }
        // kiểm tra xem có va chạm với bomb không
        for (Bomb bomb : map.getBombs()) {
            if(this.checkCollision(bomb) && !(this instanceof Player)) {
                is_col = true;
                break;
            }
        }
        // kiểm tra xem có va chạm với tường không
        for (Entity entity1 : map.getLayout1()) {
            if (!(entity1 instanceof Grass) && this.checkCollision(entity1)) {
                is_col = true;
                break;
            }
        }
        // kiểm tra xem người chơi có ăn item không
        if(this instanceof Player) {
            for(Item item : map.getSubLayout()) {
                if(this.checkCollision(item)) {
                    new Sound("bomberman-starter-starter-2/res/sound/power_up.wav").play();
                    item.toPlayer();
                    item.setBreak(true);
                    break;
                }
            }
        }
        // xu ly va cham
        if (is_col) {
            if(this instanceof Balloon) {
                ((Balloon) this).setTimeToChangeDir(0);
            }
            this.setX(this.getX() - _x);
            this.setY(this.getY() - _y);
        }
    }

    @Override
    public void update() {

    }
}
