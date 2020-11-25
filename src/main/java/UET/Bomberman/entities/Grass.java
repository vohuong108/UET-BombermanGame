package UET.Bomberman.entities;

import UET.Bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Grass extends UET.Bomberman.entities.Entity {
    private int width = Sprite.SCALED_SIZE;
    private int height = Sprite.SCALED_SIZE;

    public Grass(int x, int y, Image img) {
        super(x, y, img);
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
}
