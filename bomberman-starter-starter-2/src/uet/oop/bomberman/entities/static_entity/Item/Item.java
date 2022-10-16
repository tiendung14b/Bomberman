package uet.oop.bomberman.entities.static_entity.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Item extends Entity {
    private final String bombItem = "bomb item";
    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);

    }

    @Override
    public void update() {

    }
}
