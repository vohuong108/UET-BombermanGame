package UET.Bomberman.entities;

import UET.Bomberman.controller.PlaySound;
import UET.Bomberman.data.DataMapManager;
import UET.Bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bomb extends Entity {
    private int width = Sprite.SCALED_SIZE;
    private int height = Sprite.SCALED_SIZE;
    private String oldIMG = null;
    private Flame flame;
    public boolean isExploded = false;

    public Bomb(int x, int y, Image img, int rangeFlame) {
        super(x, y, img);
        flame = new Flame(x, y, rangeFlame);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void updateUI() {

    }

    public void activeBomb() {
        AnimationTimer timer = new AnimationTimer() {
            private long startTime;
            private long prev = 0;
            @Override
            public void start() {
                startTime = System.currentTimeMillis();
                super.start();
            }

            @Override
            public void stop() {
                Bomb.this.isExploded = true;
                super.stop();
            }

            @Override
            public void handle(long now) {
                long curTime = System.currentTimeMillis();
                if (curTime - startTime >= 2500) {
                    removeBombUI();
                    DataMapManager.mapData.put(Bomb.this.x + ";" + Bomb.this.y, "grass");
                    flame.flameActive();
                    PlaySound.playSound("BombExploded.wav");
                    stop();
                }
                else if (now - prev < 200_000_000) {
                    return;
                }
                else {
                    prev = now;
                    countdownBombUI();
                }
            }
        };
        timer.start();

    }

    private void removeBombUI() {
        DataMapManager.playLayer.getChildren().remove(this.imageView);
    }

    private void countdownBombUI() {
        if (this.oldIMG == null) {
            this.img = Sprite.bomb.getFxImage();
            this.oldIMG = "bomb";
        }
        else if (this.oldIMG.equals("bomb")) {
            this.img = Sprite.bomb_1.getFxImage();
            this.oldIMG = "bomb_1";
        }
        else if (this.oldIMG.equals("bomb_1")) {
            this.img = Sprite.bomb_2.getFxImage();
            this.oldIMG = "bomb_2";
        }
        else if (this.oldIMG.equals("bomb_2")) {
            this.img = Sprite.bomb.getFxImage();
            this.oldIMG = "bomb";
        }

        DataMapManager.playLayer.getChildren().remove(this.imageView);
        this.imageView = new ImageView(this.img);
        this.imageView.relocate(this.x * Sprite.SCALED_SIZE , this.y * Sprite.SCALED_SIZE);
        DataMapManager.playLayer.getChildren().add(this.imageView);
    }

}
