package UET.Bomberman.entities;

import UET.Bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class Portal extends Entity {
    private int width = Sprite.SCALED_SIZE;
    private int height = Sprite.SCALED_SIZE;
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void updateUI() {

    }

}
