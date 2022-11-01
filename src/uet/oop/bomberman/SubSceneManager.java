package uet.oop.bomberman;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class SubSceneManager extends SubScene {
    private static final String FONT_TEXTURE = "D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/Menu/Minecraft.ttf";
    private static final String BACKGROUND_IMAGE = "D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/Menu/bground.png";

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
