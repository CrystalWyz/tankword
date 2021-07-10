package cn.wyz.tankword.factory;

import cn.wyz.tankword.*;

import java.awt.*;
import java.util.Random;

/**
 * @author wangnanxiang
 */
public abstract class BaseTank {
    protected static int SPEED = 3;
    protected int x, y;
    protected boolean moving = true;
    protected Group group = Group.BAD;
    protected final Rectangle rectangle = new Rectangle();
    protected Dir dir;
    protected final TankFrame tankFrame;
    protected boolean live = true;
    protected final Random random = new Random();

    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    protected BaseTank(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public abstract void paint(Graphics g);

    public abstract void die();

    public abstract void move();

    public abstract void boundsCheck();

    public abstract void randomDir();

    public abstract void fire(FireStrategy fs);
}
