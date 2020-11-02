package UET.Bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import UET.Bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private String oldIMG = null;
    private int speed = 4;

    public Bomber(int x, int y, Image img, String oldIMG) {
        super( x, y, img);
        this.oldIMG = oldIMG;
    }

    @Override
    public void update(Pane layer, KeyCode e) {
        if(e == KeyCode.RIGHT || e == KeyCode.D) {
            if (this.oldIMG.equals("player_right")) {
                this.img = Sprite.player_right_1.getFxImage();
                this.oldIMG = "player_right_1";
            }
            else if (this.oldIMG.equals("player_right_1")) {
                this.img = Sprite.player_right_2.getFxImage();
                this.oldIMG = "player_right_2";
            }
            else if (this.oldIMG.equals("player_right_2")) {
                this.img = Sprite.player_right.getFxImage();
                this.oldIMG = "player_right";
            }
            else {
                this.img = Sprite.player_right.getFxImage();
                this.oldIMG = "player_right";
            }
            layer.getChildren().remove(this.imageView);
            this.imageView = new ImageView(img);
            this.x += speed;
            this.imageView.relocate(x, y);
            layer.getChildren().add(this.imageView);
        }
        else if(e == KeyCode.LEFT || e == KeyCode.A) {
            if (this.oldIMG.equals("player_left")) {
                this.img = Sprite.player_left_1.getFxImage();
                this.oldIMG = "player_left_1";
            }
            else if (this.oldIMG.equals("player_left_1")) {
                this.img = Sprite.player_left_2.getFxImage();
                this.oldIMG = "player_left_2";
            }
            else if (this.oldIMG.equals("player_left_2")) {
                this.img = Sprite.player_left.getFxImage();
                this.oldIMG = "player_left";
            }
            else {
                this.img = Sprite.player_left.getFxImage();
                this.oldIMG = "player_left";
            }
            layer.getChildren().remove(this.imageView);
            this.imageView = new ImageView(img);
            this.x -= speed;
            this.imageView.relocate(x, y);
            layer.getChildren().add(this.imageView);
        }
        else if(e == KeyCode.UP || e == KeyCode.W) {
            if (this.oldIMG.equals("player_up")) {
                this.img = Sprite.player_up_1.getFxImage();
                this.oldIMG = "player_up_1";
            }
            else if (this.oldIMG.equals("player_up_1")) {
                this.img = Sprite.player_up_2.getFxImage();
                this.oldIMG = "player_up_2";
            }
            else if (this.oldIMG.equals("player_up_2")) {
                this.img = Sprite.player_up.getFxImage();
                this.oldIMG = "player_up";
            }
            else {
                this.img = Sprite.player_up.getFxImage();
                this.oldIMG = "player_up";
            }
            layer.getChildren().remove(this.imageView);
            this.imageView = new ImageView(img);
            this.y -= speed;
            this.imageView.relocate(x, y);
            layer.getChildren().add(this.imageView);
        }
        else if(e == KeyCode.DOWN || e == KeyCode.S) {
            if (this.oldIMG.equals("player_down")) {
                this.img = Sprite.player_down_1.getFxImage();
                this.oldIMG = "player_down_1";
            }
            else if (this.oldIMG.equals("player_down_1")) {
                this.img = Sprite.player_down_2.getFxImage();
                this.oldIMG = "player_down_2";
            }
            else if (this.oldIMG.equals("player_down_2")) {
                this.img = Sprite.player_down.getFxImage();
                this.oldIMG = "player_down";
            }
            else {
                this.img = Sprite.player_down.getFxImage();
                this.oldIMG = "player_down";
            }
            layer.getChildren().remove(this.imageView);
            this.imageView = new ImageView(img);
            this.y += speed;
            this.imageView.relocate(x, y);
            layer.getChildren().add(this.imageView);
        }
    }

    @Override
    public void render(Pane layer) {
        imageView.relocate(x, y);

        layer.getChildren().add(imageView);
    }
}
