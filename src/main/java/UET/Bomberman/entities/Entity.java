package UET.Bomberman.entities;

import UET.Bomberman.data.DataMapManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import UET.Bomberman.graphics.Sprite;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;
    public ImageView imageView;

    public Entity( int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.imageView = new ImageView(img);
        imageView.relocate(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract int getWidth();

    public abstract int getHeight();

    public void render() {
        DataMapManager.playLayer.getChildren().add(imageView);
    }

    public abstract void updateUI();

    public void removeEntity() {
        DataMapManager.entities.remove(this);
        DataMapManager.playLayer.getChildren().remove(this.imageView);
    }

}
