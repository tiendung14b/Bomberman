package uet.oop.bomberman.Menu.model;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.beans.Transient;

public class SubSceneManager extends SubScene {
    private static final String FONT_TEXTURE = "D:/Workspace/Demo/BomberMan-menuTest_novaSeele/img/Minecraft.ttf";
    private static final String BACKGROUND_IMAGE = "D:/Workspace/Demo/BomberMan-menuTest_novaSeele/img/red_button11.png";

    private boolean isHidden;

    public SubSceneManager() {
        super(new AnchorPane(), 300, 200);
        prefWidth(300);
        prefHeight(200);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 300, 200, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane help = (AnchorPane) this.getRoot();

        help.setBackground(new Background(image));

        setLayoutY(100);
        setLayoutX(-600);

        isHidden = true;
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(javafx.util.Duration.seconds(0.3));
        transition.setNode(this);

        if(isHidden) {
            transition.setToX(600);
            isHidden = false;
        } else {
            transition.setToX(-600);
            isHidden = true;
        }

        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
