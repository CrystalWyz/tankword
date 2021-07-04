package cn.wyz.tankword;

import java.awt.*;
import java.util.Random;

/**
 * @author wangnanxiang
 */
public class Tank {
    private int x, y;
    private Dir dir;
    private static final int SPEED = 3;
    public static final int WIDTH = ResourceMgr.tankU.getWidth();
    public static final int HEIGHT = ResourceMgr.tankU.getHeight();
    private Group group = Group.BAD;

    public boolean live = true;

    private final TankFrame tankFrame;

    private boolean moving = true;

    private final Random random = new Random();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if(!live) {
            tankFrame.tanks.remove(this);
        }

        move();

        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            default:break;
        }
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

        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
            this.randomDir();
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = x + ResourceMgr.tankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int bY = y + ResourceMgr.tankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        tankFrame.bulletList.add(new Bullet(bX, bY, dir, this.group, tankFrame));
    }

    public void die() {
        this.live = false;
        int eX = this.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
        int eY = this.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
        tankFrame.explodeList.add(new Explode(eX, eY, tankFrame));
    }
}
