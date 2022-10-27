package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import uet.oop.bomberman.button.*;
import uet.oop.bomberman.entities.dynamic_entities.Bomber.Player;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.HashMap;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static GraphicsContext gc;
    public static Canvas canvas;
    public static AnchorPane root;
    public static Scene scene;
    public static Map map;
    public static Stage stage;
    public static Menu menu;
    public static boolean isPause = false;
    public static boolean soundOn = true;
    public static boolean isFinal = false;
    public static long lastTime = 0;
    public static Sound sound;
    public boolean isSoundOn = true;
    private SubSceneManager helpSubScene;
    public static boolean isNextLevel = false;

    public static int level = 1;

    private HashMap<Integer, String> loadlevel = new HashMap<Integer, String>() {
        {
            put(1, "D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/levels/Level1.txt");
            put(2, "D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/levels/Level2.txt");
            put(3, "D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/levels/Level3.txt");
            put(4, "D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/levels/Level4.txt");
            put(5, "D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/levels/Level5.txt");
        }
    };

    Sound bg = new Sound("D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/sound/game_title.wav");
//    public static ImageView viewImage;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        BombermanGame.stage = stage;
        root = new AnchorPane();
        scene = new Scene(root, 31 * 32, 14 * 32 + 22);
        stage.setScene(scene);
        stage.setTitle("Bomberman");
        stage.setResizable(false);
        stage.show();
        loopSound();
        intilizeMenuScreen();
    }

    public void clearEveryThing() {
        //clear all thing in root
        root.getChildren().remove(menu);
        root.getChildren().remove(canvas);
        root.getChildren().clear();
        root.setBackground(null);
    }

    public void createMap() {
        map = new Map(loadlevel.get(level), "Level " + level);
        map.setPlayer(new Player(1, 1, Sprite.player_down.getFxImage()));
        map.getPlayer().setAlive(true);
    }

    public void loopSound() {
        if (isSoundOn) {
            bg.play();
        } else {
            bg.stop();
        }
    }

    public void intilizeMenuScreen() {
        clearEveryThing();

        //add menu content
        createBackGround();
        createSubScene();

        startButton();
        soundButton();
        exitButton();
        instructionButton();
    }

    //-------------------------------------------Menu Function------------------------------------------------//
    public void createBackGround() {
        Image backgroundImage = new Image("D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/Menu/bground.png", 32*31, 14*32+22, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        root.setBackground(new Background(background));
        if(backgroundImage== null) {
            System.out.println("Background image not found");
        }
    }

    public void createSubScene() {
        helpSubScene = new SubSceneManager();
        root.getChildren().add(helpSubScene);
        helpSubScene.getPane().getChildren().add(backButtons());
    }

    public void startButton() {
        ButtonManager startButton = new ButtonManager("START");
        startButton.setLayoutX(375);
        startButton.setLayoutY(300);
        root.getChildren().add(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Start");
                intilizeGameScreen();
                isPause = false;
            }
        });
    }

    public void soundButton() {

    }

    public void exitButton() {
        ButtonManager exitButton = new ButtonManager("EXIT");
        exitButton.setLayoutX(375);
        exitButton.setLayoutY(400);
        root.getChildren().add(exitButton);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Exit");
                System.exit(0);
            }
        });
    }

    public void instructionButton() {
        ButtonManager instructionsButton = new ButtonManager("HOW TO PLAY");
        instructionsButton.setLayoutX(375);
        instructionsButton.setLayoutY(350);
        root.getChildren().add(instructionsButton);
        instructionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                helpSubScene.moveSubScene();
            }
        });
    }

    private ButtonManager backButtons() {
        ButtonManager backButton = new ButtonManager("BACK");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                helpSubScene.moveSubScene();
            }
        });
        return backButton;
    }

    //-------------------------------------------Menu Function------------------------------------------------//
    //
    //
    //
    //
    //
    //-------------------------------------------Game Function------------------------------------------------//
    public void intilizeGameScreen() {
        clearEveryThing();


        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(54);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);


        // Tao menu trong map
        Menu.gameMenu();

        //tao nut trong map
        backtoMenuButton();
        pauseButton();
        resumeButton();
        soundButtonInGame();
        skipButton();

        //tao map
        createMap();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Game Start
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!isPause) {
                    update();
                    render();
                    Menu.updateMenu(map);
                }
            }
        };
        timer.start();

        if (isPause) {
            timer.stop();
        } else {
            timer.start();
        }

        if(!map.getPlayer().isAlive()){
            System.out.println("Game Over");
        }

    }

    public void update() {
        if(level > loadlevel.size()) {
            level = 1;
            intilizeEndGameScreen();
        }
        map.updateMap();
        if (map.getTime() == 0) {
            level = 1;
            intilizeEndGameScreen();
        }
        if(isNextLevel) {
            isPause = true;
            System.out.println("Skip");
            level++;
            intilizeGameScreen();
            isPause = false;
            isNextLevel = false;
            isFinal = false;
        }
        if (!map.getPlayer().isAlive() && this.map.getPlayer().getTimeDeath() == 127) {
           new Sound("D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/sound/just_died.wav").play();
        }
        if(map.getPlayer().getTimeDeath() == 0) {
            level = 1;
            intilizeEndGameScreen();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.renderMap();
    }

    public void backtoMenuButton() {
        ButtonManagerGame backtoMenuButton = new ButtonManagerGame(".");
        backtoMenuButton.setLayoutX(0);
        backtoMenuButton.setLayoutY(0);
        root.getChildren().add(backtoMenuButton);
        backtoMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                level = 1;
                System.out.println("Back to Menu");
                intilizeMenuScreen();
                isPause = true;
                createMap();
            }
        });
    }

    public void pauseButton() {
        Pause pauseButton = new Pause(".");
        pauseButton.setLayoutX(32*29);
        pauseButton.setLayoutY(0);
        root.getChildren().add(pauseButton);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Pause");
                isPause = true;
            }
        });
    }

    public void resumeButton() {
        Resume resumeButton = new Resume(".");
        resumeButton.setLayoutX(32*27);
        resumeButton.setLayoutY(0);
        root.getChildren().add(resumeButton);
        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Resume");
                isPause = false;
            }
        });
    }

    public void soundButtonInGame() {
        SoundButtonInGame soundButton = new SoundButtonInGame(".");
        soundButton.setLayoutX(32*3);
        soundButton.setLayoutY(0);
        root.getChildren().add(soundButton);
        soundButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(isSoundOn){
                    isSoundOn = false;
                    System.out.println("Sound Off");
                    loopSound();
                } else {
                    isSoundOn = true;
                    System.out.println("Sound On");
                    loopSound();
                }
            }
        });
    }

    public void skipButton() {
        SkipLevel skipButton = new SkipLevel(".");
        skipButton.setLayoutX(32*25);
        skipButton.setLayoutY(0);
        root.getChildren().add(skipButton);
        skipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                isPause = true;
                System.out.println("Skip");
                level++;
                intilizeGameScreen();
                isPause = false;
            }
        });
    }

    //-------------------------------------------Game Function------------------------------------------------//
    //
    //
    // Nếu mà khi xảy ra thua thì gọi cái hàm intilizeEndGameScreen vào
    //
    //-------------------------------------------End Game Function------------------------------------------------//
    public void intilizeEndGameScreen() {
        clearEveryThing();
        isPause = true;
        createBackGroundMenu();
        replayButton();
        exitButton();
    }

    public void createBackGroundMenu() {
        BackgroundImage backgroundImage = new BackgroundImage(new javafx.scene.image.Image("D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/Menu/bground.png"), null, null, null, null);
        Background background = new Background(backgroundImage);
        root.setBackground(background);
    }

    public void replayButton() {
        ButtonManager replayButton = new ButtonManager("REPLAY");
        replayButton.setLayoutX(375);
        replayButton.setLayoutY(300);
        root.getChildren().add(replayButton);
        replayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Replay");
                intilizeGameScreen();
                isPause = false;
            }
        });
    }
    //-------------------------------------------End Game Function------------------------------------------------//
}
