package cn.wyz.tankword.bean;

import java.awt.*;
import java.io.Serializable;

/**
 * @author wangnanxiang
 */
public abstract class BaseGameObject implements Serializable {
    protected int x, y;
    protected static final int WIDTH = 0;
    protected static final int HEIGHT = 0;

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

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }


    /**
     * 绘画方法
     * @param g 画笔
     */
    public abstract void paint(Graphics g);
}
