package cn.wyz.tankword.bean;

import cn.wyz.tankword.constant.Group;
import cn.wyz.tankword.Mgr.ResourceMgr;
import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.factory.BaseBullet;
import cn.wyz.tankword.factory.BaseTank;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public class Bullet extends BaseBullet {
    private static final int SPEED = 15;
    public static final int WIDTH = ResourceMgr.bulletU.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletU.getHeight();
    private Boolean live = true;
    private int x, y;
    private Dir dir;
    private final TankFrame tankFrame;
    private Group group = Group.BAD;
    private final Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        this.rectangle.x = this.x;
        this.rectangle.y = y;
        this.rectangle.width = WIDTH;
        this.rectangle.height = HEIGHT;

        this.tankFrame.getBulletList().add(this);
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    @Override
    public void paint(Graphics g) {
        if(!live) {
            tankFrame.getBulletList().remove(this);
        }
        move();

        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            default:break;
        }
    }

    private void move() {
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            live = false;
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

        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
    }

    @Override
    public void collideWith(BaseTank baseTank) {
        if(this.getGroup() == baseTank.getGroup()) {
            return;
        }
        if(this.rectangle.intersects(baseTank.getRectangle())) {
            baseTank.die();
            this.die();
        }
    }

    private void die() {
        live = false;
    }
}
