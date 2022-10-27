package uet.oop.bomberman.entities.dynamic_entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamic_entities.Creature;
import uet.oop.bomberman.entities.dynamic_entities.enemy.AStar.AStar;
import uet.oop.bomberman.entities.dynamic_entities.enemy.AStar.Node;

import java.util.List;
import java.util.Random;

import static uet.oop.bomberman.BombermanGame.*;

public class Oneal extends Creature {
    private final int time = 128;
    private int timeToChangeDir;
    private int cnt;

    public int getTimeToChangeDir() {
        return timeToChangeDir;
    }

    public void setTimeToChangeDir(int timeToChangeDir) {
        this.timeToChangeDir = timeToChangeDir;
    }

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.timeToChangeDir = time;
        this.speed = 1;
        this.isMove = true;
        this.setAlive(true);
    }

    public void move() {
        if(this.getX() % 32 != 0 || this.getY() % 32 != 0) {
            return;
        }
        Node start = new Node((int) Math.round(this.getY() / 32.0), (int) Math.round(this.getX() / 32.0));
        Node player_point = new Node((int) Math.round(map.player.getY() / 32.0), (int) Math.round(map.player.getX() / 32.0));
        map.setMapping();
        AStar aStar = new AStar(map.getMapping(), HEIGHT, WIDTH, start, player_point);
        List<Node> path = aStar.getPath();
        isMove = true;
        if (path.isEmpty()) {
            this.isMove = false;
        } else {
            if(path.size() <= 2) {
                return;
            }
            int id = path.size() - 2;
            if (this.getY() / 32 > path.get(id).getI()) {
                this.setDirection("up");
            }
            if (this.getY() / 32 < path.get(id).getI()) {
                this.setDirection("down");
            }
            if (this.getX() / 32 > path.get(id).getJ()) {
                this.setDirection("left");
            }
            if (this.getX() / 32 < path.get(id).getJ()) {
                this.setDirection("right");
            }
        }
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
