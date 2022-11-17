package uet.oop.bomberman.button;

//javafx imports
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

//java imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SoundButtonInGame extends Button{
    private final String BUTTON_RELEASED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('https://i.imgur.com/xsooqc1.png')";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('https://i.imgur.com/xsooqc1.png')";
    private final String FONT_TEXTURE = "D:/Workspace/Demo3/BomberMan-tiendung2003/bomberman-starter-starter-2/res/Menu/Minecraft.ttf";

    public SoundButtonInGame(String text) {
        setText(text);
        setButtonFont();
        setPrefWidth(49);
        setPrefHeight(49);
        setStyle(BUTTON_RELEASED_STYLE);
        initializeButtonListeners();
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_TEXTURE), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
            System.out.println("Font not found");
        }
    }

    private void setButtonPressedStyle() {
        try {
            setStyle(BUTTON_PRESSED_STYLE);
        } catch (Exception e) {
            System.out.println("Button pressed style not found");
        }
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_RELEASED_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}
