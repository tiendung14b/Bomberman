package uet.oop.bomberman.Menu.view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uet.oop.bomberman.Menu.model.ButtonManagerGame;


import java.util.ArrayList;
import java.util.List;

public class GameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Group root;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

//    private static final int HEIGHT = 448;
//    private static final int WIDTH = 922;

    private static final int MENU_BUTTONS_START_X = 0;
    private static final int MENU_BUTTONS_START_Y = 0;

    private Stage menuStage;



    private AnimationTimer gameTimer;


    public GameViewManager() {
        intializeStage();

        createButton();
    }

    public void intializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, WIDTH * 32, HEIGHT * 32);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        gameStage.show();
    }
    
    private void createButton() {
        backToMenuButton();
        pauseButton();
        resumeButton();
    }

    private void backToMenuButton() {
        ButtonManagerGame backToMenuButton = new ButtonManagerGame("M");
        backToMenuButton.setLayoutX(MENU_BUTTONS_START_X);
        backToMenuButton.setLayoutY(MENU_BUTTONS_START_Y);
        gamePane.getChildren().add(backToMenuButton);
        backToMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameStage.close();
                menuStage.show();
            }
        });
    }

    private void pauseButton() {
        ButtonManagerGame pauseButton = new ButtonManagerGame("||");
        pauseButton.setLayoutX(MENU_BUTTONS_START_X + 700);
        pauseButton.setLayoutY(MENU_BUTTONS_START_Y);
        gamePane.getChildren().add(pauseButton);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pauseButton.setLayoutY(MENU_BUTTONS_START_Y);
                gameTimer.stop();
            }
        });
    }

    private void resumeButton() {
        ButtonManagerGame resumeButton = new ButtonManagerGame(">");
        resumeButton.setLayoutX(MENU_BUTTONS_START_X + 750);
        resumeButton.setLayoutY(MENU_BUTTONS_START_Y);
        gamePane.getChildren().add(resumeButton);
        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resumeButton.setLayoutY(MENU_BUTTONS_START_Y);
                gameTimer.start();
            }
        });
    }

    private void createGameLoop() {

        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
            }
        };
        gameTimer.start();
    }

    private void updateGame() {

    }
}
