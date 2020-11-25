package UET.Bomberman.gift;

import UET.Bomberman.entities.Entity;
import javafx.scene.image.Image;

public class BombItem extends Gift {
    public BombItem(int x, int y, Image img, Entity insideObj, String nameInsideObj) {
        super(x, y, img, insideObj, nameInsideObj);
    }

    @Override
    public void updateUI() {
    }
}
