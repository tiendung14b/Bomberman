package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import uet.oop.bomberman.Menu.view.ViewManager;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import javax.swing.text.View;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Group root;
    public static Scene scene;
    public static Map map;
    public static Stage stage;
    public static MenuT menuT;
    public static boolean isPause = true;
    public static boolean soundOn = true;
    public static boolean isFinal = true;
    public static long lastTime = 0;
    public static Sound sound;
//    public static ImageView viewImage;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        ViewManager viewManager = new ViewManager();
        stage = viewManager.getMainStage();
        BombermanGame.stage = stage;
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();
        root = new Group();
        root.getChildren().add(canvas);
        scene = new Scene(root);
        MenuT.gameMenu();
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!isPause) {
                    update();
                    render();
//                    MenuT.updateMenu(map);
                }
            }
        };
        timer.start();
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
