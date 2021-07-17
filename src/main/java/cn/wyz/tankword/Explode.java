package cn.wyz.tankword;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public class Explode extends BaseGameObject {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    private GameModel gameModel = null;

    private int step = 0;

    public Explode(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            gameModel.getBaseGameObjectList().remove(this);
        }
    }
}
