package UET.Bomberman;

import UET.Bomberman.entities.*;
import UET.Bomberman.graphics.Sprite;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static int WIDTH;
    public static int HEIGHT;

    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private KeyCode keyCode = null;
    Pane scoreLayer;
    Pane playLayer;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        createMap();
        initLayer();

        VBox root = new VBox();
        root.getChildren().add(scoreLayer);
        root.getChildren().add(playLayer);

        Scene scene = new Scene(root, WIDTH * Sprite.SCALED_SIZE, HEIGHT * Sprite.SCALED_SIZE);

        scene.setOnKeyPressed(e -> keyCode = e.getCode());
        scene.setOnKeyReleased(e -> keyCode = null);
        stage.setScene(scene);
        stage.show();

        renderMap();
        AnimationTimer timer = new AnimationTimer() {
            private long sleepNs = 1_000_000;
            long prevTime = 0;
            public void handle(long now) {
                if ((now - prevTime) < sleepNs) {
                    return;
                }
                prevTime = now;
                updateUI(keyCode);

            }

        };
        timer.start();


    }

    public void createMap() {
        File file = new File("src/main/resources/levels/Level1.txt");
        try {
            Scanner sc = new Scanner(file);
            String[] param = sc.nextLine().split(" ");

            int row = Integer.parseInt(param[1]);
            int col = Integer.parseInt(param[2]);

            WIDTH = col;
            HEIGHT = row;

            for (int i = 0; i < row; i++) {
                char[] temp = sc.nextLine().toCharArray();
                for (int j = 0; j < temp.length; j++) {
                    switch (temp[j]) {
                        case '#':
                            stillObjects.add(new Wall(j , i, Sprite.wall.getFxImage()));
                            break;
                        case '*':
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'x':
                            stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                            break;
                        case 'p':
                            entities.add(new Bomber(j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE, Sprite.player_right.getFxImage(), "player_right"));
                            break;
                        case '1':
                            stillObjects.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                            break;
                        case '2':
                            stillObjects.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                            break;
                        case 'b':
                            break;
                        case 'f':
                            stillObjects.add(new Flame(j, i, Sprite.powerup_flamepass.getFxImage()));
                            break;
                        case 's':
                            break;
                        default:
                            stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            break;
                    }

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void renderMap() {
        stillObjects.forEach(g -> g.render(playLayer));
        entities.forEach(g -> g.render(playLayer));
    }

    public void updateUI(KeyCode e) {
        entities.forEach(eti -> eti.update(playLayer, e));
    }

    public void initLayer() {
        BackgroundFill myBF = new BackgroundFill(Color.BLACK, new CornerRadii(0),
                new Insets(0.0,0.0,0.0,0.0));

        playLayer = new Pane();
        scoreLayer = new Pane();
        scoreLayer.setPrefHeight(2 * Sprite.SCALED_SIZE);
        scoreLayer.setBackground(new Background(myBF));

        HEIGHT += 2;

        Text collisionText = new Text();
        collisionText.setFont(Font.font(null, FontWeight.BOLD, 30));
        collisionText.setStroke(Color.BLACK);
        collisionText.setFill(Color.RED);
        collisionText.setText("SCORE: ");

        collisionText.relocate(WIDTH * Sprite.SCALED_SIZE / 2 - 10, Sprite.SCALED_SIZE / 2);

        scoreLayer.getChildren().add(collisionText);
    }
}
