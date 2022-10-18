package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamic_entities.Player;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import java.awt.*;

import uet.oop.bomberman.graphics.Map.*;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Group root;
    public static Scene scene;
    public static Map map;
    Sound sound;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
//        sound = new Sound("bomberman-starter-starter-2/res/sound/stage_theme.wav");
//        sound.play();
//        sound.loop();
        map = new Map("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/levels/Level1.txt");
        map.setPlayer(new Player(1, 1, Sprite.player_down.getFxImage()));
    }

    public void renderEndGame() {
        gc.drawImage(new Image("res/Menu/endgame.jpg"), 0, 0);
    }

    public void update() {
        map.updateMap();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.renderMap();
    }
}
