package UET.Bomberman.controller;

import UET.Bomberman.data.DataMapManager;
import UET.Bomberman.entities.Entity;
import UET.Bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.Clip;


public class SceneController {
    public static Pane initIntroStage(int level) {
        Pane stage = new Pane();
        BackgroundFill myBF = new BackgroundFill(Color.BLACK, new CornerRadii(0),
                new Insets(0.0,0.0,0.0,0.0));
        stage.setPrefSize(DataMapManager.widthMap * Sprite.SCALED_SIZE, DataMapManager.heightMap * Sprite.SCALED_SIZE);
        stage.setBackground(new Background(myBF));

        Text stageMap = new Text();
        stageMap.setFont(Font.font(null, FontWeight.BOLD, 30));
        stageMap.setStroke(Color.BLACK);
        stageMap.setFill(Color.WHITE);
        stageMap.setText("STAGE " + level);
        stageMap.resize(100, 50);
        double posX = DataMapManager.widthMap * Sprite.SCALED_SIZE / 2.0 - 50;
        double posY = DataMapManager.heightMap * Sprite.SCALED_SIZE / 2.0 - 25;
        stageMap.relocate( posX, posY);

        stage.getChildren().add(stageMap);
        return stage;
    }

    public static void loadIntroStage(Stage primaryStage, Pane introLayer) {
        VBox root = new VBox();
        root.getChildren().add(introLayer);
        Scene scene = new Scene(root, DataMapManager.widthMap * Sprite.SCALED_SIZE, DataMapManager.heightMap * Sprite.SCALED_SIZE);
        primaryStage.setScene(scene);

        PlaySound.playSound("startLevel.wav");
    }

    public static void loadNextMapStage(Stage primaryStage) {
        DataMapManager.THREAD_SOUNDTRACK.stop();

        AnimationTimer timer = new AnimationTimer() {
            private long startTime;
            @Override
            public void start() {
                startTime = System.currentTimeMillis();
                super.start();
                loadIntroStage(primaryStage, initIntroStage(DataMapManager.levelMap));
            }

            @Override
            public void stop() {
                DataMapManager.THREAD_SOUNDTRACK.loop(Clip.LOOP_CONTINUOUSLY);
                super.stop();
            }

            public void handle(long now) {
                long curTime = System.currentTimeMillis();

                if (curTime - startTime >= 3800) {
                    VBox root = new VBox(DataMapManager.playLayer);
                    Scene scene = new Scene(root, DataMapManager.widthMap * Sprite.SCALED_SIZE, DataMapManager.heightMap * Sprite.SCALED_SIZE);
                    scene.setOnKeyPressed(e -> DataMapManager.input = e.getCode());
                    primaryStage.setScene(scene);
                    DataMapManager.stillObjects.forEach(Entity::render);

                    stop();
                }
            }

        };
        timer.start();

    }
}
