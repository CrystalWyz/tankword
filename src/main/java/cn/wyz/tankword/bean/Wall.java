package cn.wyz.tankword.bean;

import cn.wyz.tankword.bean.BaseGameObject;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public class Wall extends BaseGameObject {
    private int width, height;

    private Rectangle rectangle;

    @Override
    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.rectangle = new Rectangle(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(color);
    }
}
