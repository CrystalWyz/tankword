package cn.wyz.tankword.bean;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public abstract class BaseGameObject {
    protected int x, y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * 绘画方法
     * @param g 画笔
     */
    public abstract void paint(Graphics g);
}
