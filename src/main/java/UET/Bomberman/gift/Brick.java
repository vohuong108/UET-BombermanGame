package UET.Bomberman.gift;

import UET.Bomberman.entities.Entity;
import javafx.scene.image.Image;

public class Brick extends Gift {
    public Brick(int x, int y, Image img, Entity insideObj, String nameInsideObj) {
        super(x, y, img, insideObj, nameInsideObj);
    }

    @Override
    public void updateUI() {
    }
}
