package UET.Bomberman.gift;

import UET.Bomberman.data.DataMapManager;
import UET.Bomberman.entities.Entity;
import UET.Bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Gift extends Entity {
    private int width = Sprite.SCALED_SIZE;
    private int height = Sprite.SCALED_SIZE;
    protected boolean isExist = true;
    protected Entity insideObj;
    protected String nameInsideObj;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Gift(int x, int y, Image img, Entity insideObj, String nameInsideObj) {
        super(x, y, img);
        this.insideObj = insideObj;
        this.nameInsideObj = nameInsideObj;
    }

    public void updateExist() {
        this.isExist = false;
        DataMapManager.playLayer.getChildren().remove(this.imageView);
        DataMapManager.mapData.put(this.x + ";" + this.y, this.nameInsideObj);
        DataMapManager.mapGift.put(this.x + ";" + this.y, this.insideObj);
    }
}
