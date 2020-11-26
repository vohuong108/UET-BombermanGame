package UET.Bomberman;

import UET.Bomberman.controller.SceneController;
import UET.Bomberman.data.DataMapManager;
import UET.Bomberman.entities.*;
import UET.Bomberman.entities.Character;
import UET.Bomberman.graphics.Sprite;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.sound.sampled.Clip;

public class BombermanGame extends Application {
    public static Stage primaryStageGame;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage primaryStage) {
        BombermanGame.primaryStageGame = primaryStage;
        SceneController.loadIntroStage(primaryStage, SceneController.initIntroStage(DataMapManager.levelMap));
        primaryStage.show();

        initPLayThread();
        renderMap();
        loadPlayStage(primaryStage, DataMapManager.playLayer, DataMapManager.THREAD_PLAYER);
    }

    public void renderMap() {
        DataMapManager.stillObjects.forEach(Entity::render);
        DataMapManager.entities.forEach(Character::render);
    }

    public void updateUI() {
        for (int i = 0; i < DataMapManager.entities.size(); i++) {
            DataMapManager.entities.get(i).updateUI();
        }
    }

    public void loadPlayStage(Stage primaryStage, Pane layer, AnimationTimer threadPlay) {
        VBox root = new VBox(layer);
        Scene scene = new Scene(root, DataMapManager.widthMap * Sprite.SCALED_SIZE, DataMapManager.heightMap * Sprite.SCALED_SIZE);
        scene.setOnKeyPressed(e -> DataMapManager.input = e.getCode());

        AnimationTimer timer = new AnimationTimer() {
            private long startTime;
            @Override
            public void start() {
                startTime = System.currentTimeMillis();
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
                threadPlay.start();
            }

            public void handle(long now) {
                long curTime = System.currentTimeMillis();
                if (curTime - startTime >= 3800) {
                    primaryStage.setScene(scene);
                    stop();
                }
            }

        };
        timer.start();
    }

    public void initPLayThread() {
        AnimationTimer playThread = new AnimationTimer() {
            @Override
            public void start() {
                DataMapManager.THREAD_SOUNDTRACK.loop(Clip.LOOP_CONTINUOUSLY);
                super.start();
            }

            @Override
            public void stop() {
                DataMapManager.THREAD_SOUNDTRACK.stop();
                super.stop();
            }

            private final long sleepNs = 40_000_000;
            long prevTime = 0;
            public void handle(long now) {
                if ((now - prevTime) < sleepNs) { return; }
                prevTime = now;
                updateUI();
                DataMapManager.input = null;
            }

        };
        DataMapManager.THREAD_PLAYER = playThread;
    }

}
