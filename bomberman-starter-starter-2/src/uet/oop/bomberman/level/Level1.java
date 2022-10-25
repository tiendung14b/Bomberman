package uet.oop.bomberman.level;
import javafx.scene.image.Image;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.dynamic_entities.Player;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.MenuT.*;
import static  uet.oop.bomberman.BombermanGame.*;
public class Level1 {
    public Level1() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        boolean wait = true;
        long time = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        new Sound("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/sound/stage_start.wav").play();
        while(now - time <= 3400) {
            now = System.currentTimeMillis();
        }
        gameButton.setImage(pause_button);
        sound = new Sound("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/sound/stage_theme.wav");
        sound.play();
        sound.loop();
//        viewImage.setImage(new Image("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/Menu/transparent.png"));
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map = new Map("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/levels/Level1.txt", "Level 1");
        map.setPlayer(new Player(1, 1, Sprite.player_down.getFxImage()));
        map.getPlayer().setAlive(true);
        lastTime = System.currentTimeMillis();
    }
}
