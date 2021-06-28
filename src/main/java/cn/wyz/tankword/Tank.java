package cn.wyz.tankword;

import java.awt.*;

public class Tank {
    private int x, y;
    private Dir dir = Dir.UP;
    private static final int SPEED = 10;
    public static final int WIDTH = 50, HEIGHT = 50;

    TankFrame tankFrame;

    private boolean moving = false;

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        move();
        Color color = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(color);
    }

    private void move() {
        if (!moving) {
            return;
        }
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:break;
        }
    }

    public void fire() {
        tankFrame.bullet = new Bullet(x, y, dir);
    }
}
