package cn.wyz.tankword.bean;

import cn.wyz.tankword.facade.GameModel;
import cn.wyz.tankword.mgr.ResourceMgr;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public class Explode extends BaseGameObject {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            GameModel.getInstance().getBaseGameObjectList().remove(this);
        }
    }
}
