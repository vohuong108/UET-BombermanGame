package UET.Bomberman.data;

import UET.Bomberman.controller.PlaySound;
import UET.Bomberman.entities.Bomber;
import UET.Bomberman.entities.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import javax.sound.sampled.Clip;
import java.util.List;
import java.util.Map;

public class DataMapManager {
    public static ReadMapData readMapData = new ReadMapData("Level1.txt");
    public static List<Entity> entities = readMapData.entities;
    public static List<Entity> stillObjects = readMapData.stillObjects;
    public static int levelMap = readMapData.levelMap;
    public static int widthMap = readMapData.widthMap;
    public static int heightMap = readMapData.heightMap;
    public static int amountEnemy = entities.size() - 1;
    public static Bomber player = readMapData.player;

    /** Lưu tọa độ các vật thể đang hiển thị trên layer.*/
    public static Map<String, String> mapData = readMapData.mapData;

    /**Lưu trữ tọa độ của Brick, Item.*/
    public static Map<String, Entity> mapGift = readMapData.mapGift;

    public static Clip THREAD_SOUNDTRACK = PlaySound.loopPlaySound("stage.wav");
    public static Pane playLayer = new Pane();
    public static AnimationTimer THREAD_PLAYER;
    public static KeyCode input;


    public static void updateData(String pathNextMap) {
        DataMapManager.readMapData = new ReadMapData(pathNextMap);
        DataMapManager.entities = readMapData.entities;
        DataMapManager.stillObjects = readMapData.stillObjects;
        DataMapManager.levelMap = readMapData.levelMap;
        DataMapManager.widthMap = readMapData.widthMap;
        DataMapManager.heightMap = readMapData.heightMap;
        DataMapManager.amountEnemy = entities.size() - 1;
        DataMapManager.player = readMapData.player;
        DataMapManager.mapData = readMapData.mapData;
        DataMapManager.mapGift = readMapData.mapGift;
        DataMapManager.playLayer = new Pane();
    }

    public static void main(String[] args) {
        System.out.println("level :" + DataMapManager.levelMap);
        DataMapManager.updateData("Level2.txt");
        System.out.println("level :" + DataMapManager.levelMap);
    }
}
