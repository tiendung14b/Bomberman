package uet.oop.bomberman.entities.dynamic_entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.entities.dynamic_entities.Bomber.Player;
import uet.oop.bomberman.entities.dynamic_entities.enemy.*;
import uet.oop.bomberman.entities.static_entity.Item.Item;
import uet.oop.bomberman.entities.static_entity.Object.Bomb;
import uet.oop.bomberman.entities.static_entity.Object.Flame;
import uet.oop.bomberman.entities.static_entity.Object.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Objects;

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
    // di chuyen + render di chuyen
    //-------------------------------
    public void handleAction() {
        if (!this.isMove()) {
            return;
        }

        if (this.getTimeToRefreshFrame() == 0) {
            this.setIdAnimation(this.getIdAnimation() % 4 + 1);
            if (this instanceof Player) {
                if (this.getIdAnimation() == 1 || this.getIdAnimation() == 3) {
                    if (soundOn) {
                        new Sound("D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/sound/walk_1.wav").play();
                    }
                } else if (this.getIdAnimation() == 2 || this.getIdAnimation() == 4) {
                    if (soundOn) {
                        new Sound("D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/sound/walk_2.wav").play();
                    }
                }
            }
        }

        int _x = 0, _y = 0;
        switch (this.getDirection()) {
            case "up":
                upStep();
                _y = -1 * this.getSpeed();
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
        Image idle, action1, action2;
        if (this instanceof Player) {
            idle = Sprite.player_up.getFxImage();
            action1 = Sprite.player_up_1.getFxImage();
            action2 = Sprite.player_up_2.getFxImage();
        } else if (this instanceof Balloon){
            idle = Sprite.balloom_left1.getFxImage();
            action1 = Sprite.balloom_left2.getFxImage();
            action2 = Sprite.balloom_left3.getFxImage();
        } else if (this instanceof Oneal) {
            idle = Sprite.oneal_left1.getFxImage();
            action1 = Sprite.oneal_left2.getFxImage();
            action2 = Sprite.oneal_left3.getFxImage();
        } else if (this instanceof Kondoria) {
            idle = Sprite.kondoria_left1.getFxImage();
            action1 = Sprite.kondoria_left2.getFxImage();
            action2 = Sprite.kondoria_left3.getFxImage();
        } else if (this instanceof Doll){
            idle = Sprite.doll_left1.getFxImage();
            action1 = Sprite.doll_left2.getFxImage();
            action2 = Sprite.doll_left3.getFxImage();
        } else if (this instanceof Minvo){
            idle = Sprite.minvo_left1.getFxImage();
            action1 = Sprite.minvo_left2.getFxImage();
            action2 = Sprite.minvo_left3.getFxImage();
        } else {
            return;
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
        Image idle, action1, action2;
        if (this instanceof Player) {
            idle = Sprite.player_left.getFxImage();
            action1 = Sprite.player_left_1.getFxImage();
            action2 = Sprite.player_left_2.getFxImage();
        } else if (this instanceof Balloon) {
            idle = Sprite.balloom_left1.getFxImage();
            action1 = Sprite.balloom_left2.getFxImage();
            action2 = Sprite.balloom_left3.getFxImage();
        } else if (this instanceof Oneal) {
            idle = Sprite.oneal_left1.getFxImage();
            action1 = Sprite.oneal_left2.getFxImage();
            action2 = Sprite.oneal_left3.getFxImage();
        } else if (this instanceof Kondoria) {
            idle = Sprite.kondoria_left1.getFxImage();
            action1 = Sprite.kondoria_left2.getFxImage();
            action2 = Sprite.kondoria_left3.getFxImage();
        } else if (this instanceof Doll && !((Doll) this).isHide()) {
            idle = Sprite.doll_left1.getFxImage();
            action1 = Sprite.doll_left2.getFxImage();
            action2 = Sprite.doll_left3.getFxImage();
        } else if (this instanceof Minvo) {
            idle = Sprite.minvo_left1.getFxImage();
            action1 = Sprite.minvo_left2.getFxImage();
            action2 = Sprite.minvo_left3.getFxImage();
        } else {
            return;
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
        Image idle, action1, action2;
        if (this instanceof Player) {
            idle = Sprite.player_right.getFxImage();
            action1 = Sprite.player_right_1.getFxImage();
            action2 = Sprite.player_right_2.getFxImage();
        } else if (this instanceof Balloon) {
            idle = Sprite.balloom_right1.getFxImage();
            action1 = Sprite.balloom_right2.getFxImage();
            action2 = Sprite.balloom_right3.getFxImage();
        } else if (this instanceof Oneal) {
            idle = Sprite.oneal_right1.getFxImage();
            action1 = Sprite.oneal_right2.getFxImage();
            action2 = Sprite.oneal_right3.getFxImage();
        } else if (this instanceof Kondoria) {
            idle = Sprite.kondoria_right1.getFxImage();
            action1 = Sprite.kondoria_right2.getFxImage();
            action2 = Sprite.kondoria_right3.getFxImage();
        } else if (this instanceof Doll && !((Doll) this).isHide()) {
            idle = Sprite.doll_right1.getFxImage();
            action1 = Sprite.doll_right2.getFxImage();
            action2 = Sprite.doll_right3.getFxImage();
        } else if (this instanceof Minvo) {
            idle = Sprite.minvo_right1.getFxImage();
            action1 = Sprite.minvo_right2.getFxImage();
            action2 = Sprite.minvo_right3.getFxImage();
        } else {
            return;
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
        Image idle, action1, action2;
        if (this instanceof Player) {
            idle = Sprite.player_down.getFxImage();
            action1 = Sprite.player_down_1.getFxImage();
            action2 = Sprite.player_down_2.getFxImage();
        } else if (this instanceof Balloon) {
            idle = Sprite.balloom_right1.getFxImage();
            action1 = Sprite.balloom_right2.getFxImage();
            action2 = Sprite.balloom_right3.getFxImage();
        } else if (this instanceof Oneal) {
            idle = Sprite.oneal_right1.getFxImage();
            action1 = Sprite.oneal_right2.getFxImage();
            action2 = Sprite.oneal_right3.getFxImage();
        } else if (this instanceof Kondoria) {
            idle = Sprite.kondoria_right1.getFxImage();
            action1 = Sprite.kondoria_right2.getFxImage();
            action2 = Sprite.kondoria_right3.getFxImage();
        } else if (this instanceof Doll && !((Doll) this).isHide()) {
            idle = Sprite.doll_right1.getFxImage();
            action1 = Sprite.doll_right2.getFxImage();
            action2 = Sprite.doll_right3.getFxImage();
        } else if (this instanceof Minvo) {
            idle = Sprite.minvo_right1.getFxImage();
            action1 = Sprite.minvo_right2.getFxImage();
            action2 = Sprite.minvo_right3.getFxImage();
        } else {
            return;
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
        if (timeToRefreshFrame == 0) {
            this.animationDeath++;
        }
        Image action1, action2, action3, action4;
        if (this instanceof Player) {
            action1 = Sprite.player_dead1.getFxImage();
            action2 = Sprite.player_dead2.getFxImage();
            action3 = Sprite.player_dead3.getFxImage();
            action4 = Sprite.player_dead3.getFxImage();
        } else if (this instanceof Balloon) {
            action1 = Sprite.balloom_dead.getFxImage();
            action2 = Sprite.mob_dead1.getFxImage();
            action3 = Sprite.mob_dead2.getFxImage();
            action4 = Sprite.mob_dead3.getFxImage();
        } else if (this instanceof Oneal) {
            action1 = Sprite.oneal_dead.getFxImage();
            action2 = Sprite.mob_dead1.getFxImage();
            action3 = Sprite.mob_dead2.getFxImage();
            action4 = Sprite.mob_dead3.getFxImage();
        } else if (this instanceof Kondoria) {
            action1 = Sprite.kondoria_dead.getFxImage();
            action2 = Sprite.mob_dead1.getFxImage();
            action3 = Sprite.mob_dead2.getFxImage();
            action4 = Sprite.mob_dead3.getFxImage();
        } else if (this instanceof Doll && !((Doll) this).isHide()) {
            action1 = Sprite.doll_dead.getFxImage();
            action2 = Sprite.mob_dead1.getFxImage();
            action3 = Sprite.mob_dead2.getFxImage();
            action4 = Sprite.mob_dead3.getFxImage();
        } else if (this instanceof Minvo) {
            action1 = Sprite.minvo_dead.getFxImage();
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
        int left_this = this.x + this.getFixLeft();
        int right_this = this.x + 32 + this.getFixRight();
        int top_this = this.y + this.getFixTop();
        int bottom_this = this.y + 32 + this.getFixBottom();
        //--------------------------------------------------------------
        int left_entity = entity.getX() + entity.getFixLeft();
        int right_entity = entity.getX() + 32 + entity.getFixRight();
        int top_entity = entity.getY() + entity.getFixTop();
        int bottom_entity = entity.getY() + 32 + entity.getFixBottom();

        // check trai tren
        if (left_this > left_entity && left_this < right_entity) {
            if (top_this > top_entity && top_this < bottom_entity) {
                return true;
            } else if (bottom_this > top_entity && bottom_this < bottom_entity) {
                return true;
            }
        }

        // check phai tren
        if (right_this > left_entity && right_this < right_entity) {
            if (top_this > top_entity && top_this < bottom_entity) {
                return true;
            } else if (bottom_this > top_entity && bottom_this < bottom_entity) {
                return true;
            }
        }

        // check trai tren
        if (left_entity > left_this && left_entity < right_this) {
            if (top_entity > top_this && top_entity < bottom_this) {
                return true;
            } else if (bottom_entity > top_this && bottom_entity < bottom_this) {
                return true;
            }
        }

        // check trai duoi
        if (right_entity > left_this && right_entity < right_this) {
            if (top_entity > top_this && top_entity < bottom_this) {
                return true;
            } else if (bottom_entity > top_this && bottom_entity < bottom_this) {
                return true;
            }
        }

        return top_this == top_entity && right_this == right_entity && bottom_this == bottom_entity;
    }

    public void handleCollision(int _x, int _y) {
        boolean is_col = false;
        // kiểm tra xem có va chạm với flame không
        for (Flame flame : map.getFlames()) {
            if (this.checkCollision(flame)) {
                if(this instanceof Oneal) {
                    System.out.println("checked");
                }
                this.setAlive(false);
            }
        }
        // kiểm tra xem người có va chạm với quái không
        if (this instanceof Player) {
            for (Creature creature : map.getEnemy()) {
                if (this.checkCollision(creature) && creature.isAlive()) {
                    this.setAlive(false);
                }
            }
        }
        if (!(this instanceof Player)) {
            if(this.checkCollision(map.getPlayer())) {
                map.getPlayer().setAlive(false);
            }
        }
        // kiểm tra xem có va chạm với bomb không
        for (Bomb bomb : map.getBombs()) {
            if (this instanceof Player && !this.checkCollision(bomb)) {
                bomb.setNewBomb(false);
            }
            if (this.checkCollision(bomb) && (this instanceof Player && !bomb.isNewBomb())) {
                is_col = true;
                break;
            }
            if (this.checkCollision(bomb) && (!(this instanceof Player))) {
                is_col = true;
                break;
            }
        }
        // kiểm tra xem có va chạm với tường không
        for (Entity entity1 : map.getLayout1()) {
            if (!(entity1 instanceof Grass) && this.checkCollision(entity1) && !(this instanceof Doll)) {
                is_col = true;
                break;
            }
        }
        // danh cho doll
        if (this instanceof Doll) {
            if(this.getX() / 32 < 1 || this.getX() / 32 > map.getWidthMap() - 2 || this.getY() / 32 < 1 || this.getY() / 32 > map.getHeightMap() - 2) {
                is_col = true;
                ((Doll) this).setTimeToChangeDir(0);
            }
        }
        // kiểm tra xem người chơi có ăn item không
        if (this instanceof Player) {
            for (Item item : map.getSubLayout()) {
                if (this.checkCollision(item)) {
                    if (!Objects.equals(item.getType(), "portal")) {
                        new Sound("D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/sound/power_up.wav").play();
                        item.setBreak(true);
                    }
                    item.toPlayer();
                    break;
                }
            }
        }
        // xu ly va cham
        if (is_col) {
            if (this instanceof Balloon) {
                ((Balloon) this).setTimeToChangeDir(0);
            } else if (this instanceof Oneal) {
                ((Oneal) this).setTimeToChangeDir(0);
            } else if (this instanceof Kondoria) {
                ((Kondoria) this).setTimeToChangeDir(0);
            } else if (this instanceof Minvo) {
                ((Minvo) this).setTimeToChangeDir(0);
            }
            this.setX(this.getX() - _x);
            this.setY(this.getY() - _y);
        }
    }

    @Override
    public void update() {

    }
}
