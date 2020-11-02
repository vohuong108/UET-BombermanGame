package UET.Bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Flame extends Entity{
    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Pane layer, KeyCode event) {

    }
}
