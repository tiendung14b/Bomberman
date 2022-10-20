package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.static_entity.Object.Brick;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    private int fixLeft = 0;
    private int fixRight = 0;
    private int fixTop = 0;
    private int fixBottom = 0;

    protected Image img;
    protected boolean breakable;
    protected boolean isBreak;
    protected int timeRemove = 1;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        if (this instanceof Brick) {
            this.breakable = true;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFixLeft() {
        return fixLeft;
    }

    public void setFixLeft(int fixLeft) {
        this.fixLeft = fixLeft;
    }

    public int getFixRight() {
        return fixRight;
    }

    public void setFixRight(int fixRight) {
        this.fixRight = fixRight;
    }

    public int getFixTop() {
        return fixTop;
    }

    public void setFixTop(int fixTop) {
        this.fixTop = fixTop;
    }

    public int getFixBottom() {
        return fixBottom;
    }

    public void setFixBottom(int fixBottom) {
        this.fixBottom = fixBottom;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public int getTimeRemove() {
        return timeRemove;
    }

    public void setTimeRemove(int timeRemove) {
        this.timeRemove = timeRemove;
    }

    public boolean isBreak() {
        return isBreak;
    }

    public void setBreak(boolean aBreak) {
        isBreak = aBreak;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();
}
