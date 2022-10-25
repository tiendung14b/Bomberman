package uet.oop.bomberman.Menu.view;

//this class is used to manage our application's windows
//three stat
//layout
//scene
//stage

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import uet.oop.bomberman.Menu.model.ButtonManager;
import uet.oop.bomberman.Menu.model.SubSceneManager;
import uet.oop.bomberman.Menu.view.GameViewManager;
import uet.oop.bomberman.level.Level1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int HEIGHT = 448;
    private static final int WIDTH = 992;

    private static final int BUTTON_START_X = 380;
    private static final int BUTTON_START_Y = 270;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    List<ButtonManager> menuButtons;

    private SubSceneManager helpSubScene;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButton();
        createBackground();
        createSubScene();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createButton() {
        startButton();
        instructionsButton();
        quitButton();
    }

    private void createSubScene() {
        helpSubScene = new SubSceneManager();
        mainPane.getChildren().add(helpSubScene);
        helpSubScene.getPane().getChildren().add(backButtons());
    }

    private void addMenuButton(ButtonManager button) {
        button.setLayoutX(BUTTON_START_X);
        button.setLayoutY(BUTTON_START_Y + menuButtons.size() * 50);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void startButton() {
        ButtonManager startButton = new ButtonManager("START");
        addMenuButton(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Level1();
                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(mainStage);
            }
        });
    }

    private void instructionsButton() {
        ButtonManager instructionsButton = new ButtonManager("HOW TO PLAY");
        addMenuButton(instructionsButton);
        instructionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                helpSubScene.moveSubScene();
            }
        });
    }

    private void quitButton() {
        ButtonManager quitButton = new ButtonManager("QUIT");
        addMenuButton(quitButton);
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }

    private ButtonManager backButtons() {
        ButtonManager backButton = new ButtonManager("BACK");
        backButton.setLayoutX(100);
        backButton.setLayoutY(100);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                helpSubScene.moveSubScene();
            }
        });
        return backButton;
    }

    private void createBackground() {
        Image backgroundImage = new Image("D:/Workspace/Demo/BomberMan-menuTest_novaSeele/img/bg.png", 992, 448, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
        if(backgroundImage== null) {
            System.out.println("Background image not found");
        }
    }
}
