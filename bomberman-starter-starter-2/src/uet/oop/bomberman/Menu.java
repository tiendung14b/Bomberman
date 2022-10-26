package uet.oop.bomberman;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Map;

import static uet.oop.bomberman.BombermanGame.*;

public class Menu {
    static Map map;
    public static ImageView gameButton;
    private static ImageView soundButton;
    private static ImageView startGameButton;
    public static final Image pause_button = new Image("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/Menu/pause_button.png");
    public static final Image resume_button = new Image("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/Menu/resumeButton.png");
    public static Text level, bomb, maxBomb, timeRemain;

    public Menu() {
    }

    public void renderStage(String level) {
//        Text title = new Text("Stage " + level);
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
//        title.setX(416);
//        title.setY(192);
//        root.getChildren().add(title);
    }

    public void mainMenu() {

    }

    public static void gameMenu() {

        level = new Text("Level 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(416);
        level.setY(20);

        bomb = new Text("Bomb: " + 1);
        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bomb.setFill(Color.WHITE);
        bomb.setX(512);
        bomb.setY(20);

        maxBomb = new Text("Bomb: " + 1);
        maxBomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        maxBomb.setFill(Color.WHITE);
        maxBomb.setX(608);
        maxBomb.setY(20);

        timeRemain = new Text("Time: " + Map.getMaxTime());
        timeRemain.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        timeRemain.setFill(Color.WHITE);
        timeRemain.setX(704);
        timeRemain.setY(20);

//        gameButton = new ImageView("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/Menu/start_button.png");

        Pane pane = new Pane();
        pane.getChildren().addAll(level, bomb, maxBomb, timeRemain);
        pane.setMinSize(992, 32);
        pane.setMaxSize(800, 480);
        pane.setStyle("-fx-background-color: #132639");

        root.getChildren().add(pane);
        updateMenu(map);


//        gameButton.setOnMouseClicked(mouseEvent -> {
//            if(map != null && map.getPlayer().isAlive()) {
//                isPause = !isPause;
//            } else {
//                new Level1();
//                isPause = false;
//            }
//            updateMenu(map);
//        });
    }

    public static void updateMenu(Map map) {
        if(map == null) {
            return;
        }
        Menu.map = map;
        long time = System.currentTimeMillis();

        if (time - lastTime > 1000) {
            lastTime = time;
            Menu.map.setTime(Menu.map.getTime() - 1);
        }

        level.setText(Menu.map.getLevel());
        maxBomb.setText("Max bomb: " + map.getMaxBomb());
        bomb.setText("Bomb: " + (map.getMaxBomb() - map.getBombs().size()));
        timeRemain.setText("Time: " + (Menu.map.getTime()));

//        gameButton.setOnMouseClicked(mouseEvent -> {
//            if (isPause) {
//                isPause = false;
//                gameButton.setImage(pause_button);
//            } else {
//                isPause = true;
//                gameButton.setImage(resume_button);
//            }
//        });
    }
}
