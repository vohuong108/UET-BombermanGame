package UET.Bomberman.data;

import UET.Bomberman.entities.*;
import UET.Bomberman.gift.*;
import UET.Bomberman.entities.Portal;
import UET.Bomberman.graphics.Sprite;
import java.io.File;
import java.util.*;

public class ReadMapData {
    public List<Entity> entities = new ArrayList<>();
    public List<Entity> stillObjects = new ArrayList<>();
    public int widthMap;
    public int heightMap;
    public Map<String, String> mapData = new HashMap<>();
    public Map<String, Entity> mapGift = new HashMap<>();
    public Bomber player;
    public int levelMap;

    public ReadMapData(String pathLevel) {
        readMapData(pathLevel);
    }

    private void readMapData(String pathLevel) {
        System.out.println(pathLevel + " READING MAP DATA.....");
        File file = new File("src/main/resources/levels/" + pathLevel);
        try {
            Scanner sc = new Scanner(file);
            String[] param = sc.nextLine().split(" ");

            int row = Integer.parseInt(param[1]);
            int col = Integer.parseInt(param[2]);

            this.levelMap = Integer.parseInt(param[0]);
            this.widthMap = col;
            this.heightMap = row;

            for (int i = 0; i < row; i++) {
                char[] temp = sc.nextLine().toCharArray();
                for (int j = 0; j < temp.length; j++) {
                    switch (temp[j]) {
                        case '#':
                            mapData.put(j + ";" + i, "wall");
                            stillObjects.add(new Wall(j , i, Sprite.wall.getFxImage()));
                            break;
                        case '*':
                            mapData.put(j + ";" + i, "brick");
                            Entity insideBrick = new Grass(j, i, Sprite.grass.getFxImage());
                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage(), insideBrick, "grass");
                            stillObjects.add(insideBrick);
                            stillObjects.add(brick);
                            mapGift.put(j + ";" + i, brick);
                            break;
                        case 'x':
                            mapData.put(j + ";" + i, "brick");
                            Entity belowPortal = new Grass(j, i, Sprite.grass.getFxImage());
                            Entity portal = new Portal(j, i, Sprite.portal.getFxImage());
                            Entity containPortal = new Brick(j, i, Sprite.brick.getFxImage(), portal, "portal");
                            stillObjects.add(belowPortal);
                            stillObjects.add(portal);
                            stillObjects.add(containPortal);
                            mapGift.put(j + ";" + i, containPortal);
                            break;
                        case 'p':
                            mapData.put(j + ";" + i, "grass");
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            player = new Bomber(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.player_right.getFxImage(), "player_right");
                            entities.add(player);
                            break;
                        case '1':
                            mapData.put(j + ";" + i, "ballon");
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            entities.add(new Balloon(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage()));
                            break;
                        case '2':
                            mapData.put(j + ";" + i, "oneal");
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            entities.add(new Oneal(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.oneal_left1.getFxImage()));
                            break;
                        case 'b':
                            mapData.put(j + ";" + i, "brick");
                            Entity insideBomb = new Grass(j, i, Sprite.grass.getFxImage());
                            Entity bomb = new BombItem(j, i, Sprite.powerup_bombs.getFxImage(), insideBomb, "grass");
                            Entity cotainBomb = new Brick(j, i, Sprite.brick.getFxImage(), bomb, "bomb item");
                            stillObjects.add(insideBomb);
                            stillObjects.add(bomb);
                            stillObjects.add(cotainBomb);
                            mapGift.put(j + ";" + i, cotainBomb);
                            break;
                        case 'f':
                            mapData.put(j + ";" + i, "brick");
                            Entity insideFlame = new Grass(j, i, Sprite.grass.getFxImage());
                            Entity flame = new FlameItem(j, i, Sprite.powerup_flames.getFxImage(), insideFlame, "grass");
                            Entity cotainFlame = new Brick(j, i, Sprite.brick.getFxImage(), flame, "flame item");
                            stillObjects.add(insideFlame);
                            stillObjects.add(flame);
                            stillObjects.add(cotainFlame);
                            mapGift.put(j + ";" + i, cotainFlame);
                            break;
                        case 's':
                            mapData.put(j + ";" + i, "brick");
                            Entity insideSpeed = new Grass(j, i, Sprite.grass.getFxImage());
                            Entity speed = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage(), insideSpeed, "grass");
                            Entity containSpeed = new Brick(j, i, Sprite.brick.getFxImage(), speed, "speed item");
                            stillObjects.add(insideSpeed);
                            stillObjects.add(speed);
                            stillObjects.add(containSpeed);
                            mapGift.put(j + ";" + i, containSpeed);
                            break;
                        default:
                            mapData.put(j + ";" + i, "grass");
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            break;
                    }

                }
            }
            System.out.println("DONE !!!");
        } catch (Exception e) {
            System.out.println(pathLevel + "ERROR LOAD DATA MAP !!!");
            e.printStackTrace();
        }
    }

}
