package UET.Bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import UET.Bomberman.graphics.Sprite;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;
    protected ImageView imageView;
    public Entity( int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.imageView = new ImageView(img);
    }

    public void render(Pane layer) {
        imageView.relocate(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);

        layer.getChildren().add(imageView);
    }
    public abstract void update(Pane layer, KeyCode event);


}
