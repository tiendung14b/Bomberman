package uet.oop.bomberman.graphics;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamic_entities.enemy.Balloon;
import uet.oop.bomberman.entities.dynamic_entities.Creature;
import uet.oop.bomberman.entities.dynamic_entities.Player;
import uet.oop.bomberman.entities.dynamic_entities.enemy.Oneal;
import uet.oop.bomberman.entities.static_entity.Item.Item;
import uet.oop.bomberman.entities.static_entity.Object.*;

import static uet.oop.bomberman.Menu.*;
import static uet.oop.bomberman.BombermanGame.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    public String level;
    private int levelMap;
    private int heightMap;
    private int widthMap;
    private static final int maxTime = 120;
    private int time = maxTime;
    private boolean isFinal = false;

    private List<Entity> layout1 = new ArrayList<>(); // chứa những object mà người chơi va chạm vào sẽ không di chuyển được
    private List<Entity> layout2 = new ArrayList<>(); // chứa những object mà người chơi có thể đi qua, những object này được render dưới lớp layout1
    private List<Item> subLayout = new ArrayList<>(); // layout này để chứ item
    private List<Bomb> bombs = new ArrayList<>(); // chứa object bomb trên bản đồ, bomb sẽ tự hủy nếu hết thời gian tồn tại
    private static int maxBomb; // max bomb mà người chơi có thể đặt vào bản đồ, tăng khi người chơi ăn item, khởi tạo gốc trong constructor
    private List<Flame> flames = new ArrayList<>(); // chứa object là lửa sau khi bom nổ, flame sẽ tồn tại một khoảng thời gian là timeImpact, flame sẽ bị chặn mất khi gặp tường
    private List<Creature> enemy = new ArrayList<>(); // chứa object là quái vật trong game
    public Player player; // đối tượng người chơi
    public boolean[][] mapping = new boolean[HEIGHT][WIDTH];

    // constructor tạo map, load ảnh từ file text
    public Map(String levelPath, String level) {
        final File file = new File(levelPath);
        try (FileReader fileReader = new FileReader(file)) {
            Scanner scanner = new Scanner(fileReader);
            levelMap = scanner.nextInt();
            heightMap = scanner.nextInt();
            widthMap = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < heightMap; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < widthMap; j++) {
                    if (line.charAt(j) == '#') {
                        layout1.add(new Wall(j, i, Sprite.wall.getFxImage()));
                    } else if (line.charAt(j) == '*') {
                        layout1.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        layout2.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    } else if (line.charAt(j) == '1') {
                        enemy.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                        layout2.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    } else if (line.charAt(j) == '2') {
                        enemy.add(new Oneal(j, i, Sprite.oneal_right1.getFxImage()));
                        layout2.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    } else {
                        layout2.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    }
                }
            }
        } catch (IOException ioException) {
            System.out.println("Loi doc file roi agggghghhhhhh");
            ioException.printStackTrace();
        }
        maxBomb = 1;
        this.level = level;
        subLayout.add(new Item(7, 1, Sprite.powerup_bombs.getFxImage(), "powerUpBomb"));
        subLayout.add(new Item(10, 9, Sprite.powerup_flames.getFxImage(), "powerUpFlame"));
        subLayout.add(new Item(6, 9, Sprite.powerup_detonator.getFxImage(), "detonator"));
        subLayout.add(new Item(12, 3, Sprite.powerup_speed.getFxImage(), "speed"));
        subLayout.add(new Item(16, 11, Sprite.portal.getFxImage(), "portal"));
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public static int getMaxTime() {
        return maxTime;
    }

    public int getLevelMap() {
        return levelMap;
    }

    public void setLevelMap(int levelMap) {
        this.levelMap = levelMap;
    }

    public int getHeightMap() {
        return heightMap;
    }

    public void setHeightMap(int heightMap) {
        this.heightMap = heightMap;
    }

    public int getWidthMap() {
        return widthMap;
    }

    public void setWidthMap(int widthMap) {
        this.widthMap = widthMap;
    }

    public int getMaxBomb() {
        return maxBomb;
    }

    public void setMaxBomb(int maxBomb) {
        Map.maxBomb = maxBomb;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean[][] getMapping() {
        return mapping;
    }

    public void setMapping() {
        for(Entity entity : layout1) {
            mapping[entity.getY() / 32][entity.getX() / 32] = true;
        }
        for(Bomb bomb : bombs) {
            mapping[bomb.getY() / 32][bomb.getX() / 32] = true;
        }
        for(Flame flame : flames) {
            mapping[flame.getY() / 32][flame.getX() / 32] = true;
        }
    }

    public void addBomb(Bomb bomb) {
        for (Creature creature : enemy) {
            if (creature.checkCollision(bomb) && bomb.getTime() == 128) {
                return;
            }
        }
        if (this.bombs.size() < maxBomb) {
            bomb.setNewBomb(true);
            new Sound("bomberman-starter-starter-2/res/sound/placed_bomb.wav").play();
            this.bombs.add(bomb);
        }
    }

    public void triggeredBomb() {
        if (bombs.size() == 0) return;
        if (bombs.get(0).getTime() != 0) return;
        if(soundOn) {
            new Sound("bomberman-starter-starter-2/res/sound/bomb_explored.wav").play();
        }
        // if time of bomb equals 0
        Bomb bomb = bombs.get(0);
        // flame up
        for (int i = 1; i <= Flame.getPower(); i++) {
            Entity entity = getLayout1At(bomb.getX(), bomb.getY() - i * 32);
            if (entity != null) {
                if (entity.isBreakable()) {
                    entity.setBreak(true);
                }
                break;
            }
            if (i == Flame.getPower()) {
                flames.add(new Flame(bomb.getX() / 32, bomb.getY() / 32 - i, null, "upLast"));
            } else {
                flames.add(new Flame(bomb.getX() / 32, bomb.getY() / 32 - i, null, "up"));
            }
        }
        // flame down
        for (int i = 1; i <= Flame.getPower(); i++) {
            Entity entity = getLayout1At(bomb.getX(), bomb.getY() + i * 32);
            if (entity != null) {
                if (entity.isBreakable()) {
                    entity.setBreak(true);
                }
                break;
            }
            if (i == Flame.getPower()) {
                flames.add(new Flame(bomb.getX() / 32, bomb.getY() / 32 + i, null, "downLast"));
            } else {
                flames.add(new Flame(bomb.getX() / 32, bomb.getY() / 32 + i, null, "down"));
            }
        }
        // flame left
        for (int i = 1; i <= Flame.getPower(); i++) {
            Entity entity = getLayout1At(bomb.getX() - i * 32, bomb.getY());
            if (entity != null) {
                if (entity.isBreakable()) {
                    entity.setBreak(true);
                }
                break;
            }
            if (i == Flame.getPower()) {
                flames.add(new Flame(bomb.getX() / 32 - i, bomb.getY() / 32, null, "leftLast"));
            } else {
                flames.add(new Flame(bomb.getX() / 32 - i, bomb.getY() / 32, null, "left"));
            }
        }
        // flame right
        for (int i = 1; i <= Flame.getPower(); i++) {
            Entity entity = getLayout1At(bomb.getX() + i * 32, bomb.getY());
            if (entity != null) {
                if (entity.isBreakable()) {
                    entity.setBreak(true);
                }
                break;
            }
            if (i == Flame.getPower()) {
                flames.add(new Flame(bomb.getX() / 32 + i, bomb.getY() / 32, null, "rightLast"));
            } else {
                flames.add(new Flame(bomb.getX() / 32 + i, bomb.getY() / 32, null, "right"));
            }
        }
        flames.add(new Flame(bomb.getX() / 32, bomb.getY() / 32, null, "center"));
        bombs.remove(0);
    }

    public List<Entity> getLayout1() {
        return layout1;
    }

    public void setLayout1(List<Entity> layout1) {
        this.layout1 = layout1;
    }

    public List<Entity> getLayout2() {
        return layout2;
    }

    public void setLayout2(List<Entity> layout2) {
        this.layout2 = layout2;
    }

    public List<Item> getSubLayout() {
        return subLayout;
    }

    public void setSubLayout(List<Item> subLayout) {
        this.subLayout = subLayout;
    }

    // hàm này có thể được dùng cho cả việc kiểm tra xem tại tọa độ
    // đó có tồn tại object hay không, cũng có thể được dùng cho việc lấy ra
    // một object tại tọa độ đó
    public Entity getLayout1At(int x, int y) {
        for (Entity entity : layout1) {
            if (entity.getX() == x && entity.getY() == y) {
                return entity;
            }
        }
        return null;
    }

    // hàm này có thể được dùng cho cả việc kiểm tra xem tại tọa độ
    // đó có tồn tại object hay không, cũng có thể được dùng cho việc lấy ra
    // một object tại tọa độ đó
    public Entity getLayout2At(int x, int y) {
        for (Entity entity : layout2) {
            if (entity.getX() == x && entity.getY() == y) {
                return entity;
            }
        }
        return null;
    }

    // hàm này có thể được dùng cho cả việc kiểm tra xem tại tọa độ
    // đó có tồn tại item hay không, cũng có thể được dùng cho việc lấy ra
    // một object item tại tọa độ đó
    public Entity getSubLayoutAt(int x, int y) {
        for (Entity entity : subLayout) {
            if (entity.getX() == x && entity.getY() == y) {
                return entity;
            }
        }
        return null;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void setBombs(List<Bomb> bombs) {
        this.bombs = bombs;
    }

    public List<Flame> getFlames() {
        return flames;
    }

    public void setFlames(List<Flame> flames) {
        this.flames = flames;
    }

    public List<Creature> getEnemy() {
        return enemy;
    }

    public void setEnemy(List<Creature> enemy) {
        this.enemy = enemy;
    }

    public void updateMap() {
        // update new status of object
        triggeredBomb();
        flames.forEach(Entity::update);
        player.update();
        bombs.forEach(Entity::update);
        layout1.forEach(Entity::update);
        subLayout.forEach(Entity::update);
        layout2.forEach(Entity::update);
        enemy.forEach(Entity::update);
        // remove object when it was destroyed or out of time
        flames.removeIf(value -> value.getTimeImpact() == 0);
        layout1.removeIf(value -> value.getTimeRemove() == 0);
        enemy.removeIf(value -> value.getTimeDeath() == 0);
        subLayout.removeIf(Entity::isBreak);
        if(!player.isAlive()) {
            gameButton.setImage(new Image("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/Menu/start_button.png"));
//            viewImage.setImage(new Image("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/Menu/bground.png"));
            isFinal = true;
            sound.stop();
        }
        if (!isFinal && enemy.size() == 0) {
            isFinal = true;
            new Sound("bomberman-starter-starter-2/res/sound/next_level.wav").play();
            gameButton.setImage(new Image("D:/Workspace/Project/OP_Bomberman/bomberman-starter-starter-2/res/Menu/start_button.png"));
        }
    }

    public void renderMap() {
        layout2.forEach(g -> g.render(gc));
        subLayout.forEach(g -> g.render(gc));
        layout1.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        player.render(gc);
        flames.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
    }
}
