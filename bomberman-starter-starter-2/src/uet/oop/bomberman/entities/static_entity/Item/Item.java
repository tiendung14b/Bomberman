package uet.oop.bomberman.entities.static_entity.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.static_entity.Object.Flame;

import static uet.oop.bomberman.BombermanGame.*;

public class Item extends Entity {
    private String type;

    public Item(int xUnit, int yUnit, Image img, String type) {
        super(xUnit, yUnit, img);
        this.setFixLeft(16);
        this.setFixTop(16);
        this.setFixRight(-16);
        this.setFixBottom(-16);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void toPlayer() {
        switch (type) {
            case "powerUpBomb":
                map.setMaxBomb(map.getMaxBomb() + 1);
                break;
            case "powerUpFlame":
                Flame.setPower(Flame.getPower() + 1);
                break;
            case "detonator":
                map.getPlayer().setNumberOfPlay(map.getPlayer().getNumberOfPlay() + 1);
                break;
            case "speed":
                map.getPlayer().setSpeed(map.getPlayer().getSpeed() + 1);
                break;
            case "portal":
                if(isFinal) {
                    isNextLevel = true;
                }
                break;
            default:
                //todo
                break;
        }
    }

    @Override
    public void update() {

    }
}
