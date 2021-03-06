package cn.wyz.tankword.bean;

import cn.wyz.tankword.constant.Group;
import cn.wyz.tankword.mgr.ResourceMgr;
import cn.wyz.tankword.net.bean.TankJoinMsg;
import cn.wyz.tankword.ui.TankFrame;
import cn.wyz.tankword.constant.Dir;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

/**
 * @author wangnanxiang
 */
public class Tank {
    private int x, y;
    private Dir dir;
    private static final int SPEED = 3;
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
    private Group group = Group.BAD;
    public boolean live = true;
    private final TankFrame tankFrame;
    private boolean moving = true;
    private final Random random = new Random();
    private final Rectangle rectangle = new Rectangle();
    private final UUID uuid;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        if(group == Group.GOOD) {
            moving = false;
        }

        this.rectangle.x = x;
        this.rectangle.y = y;
        this.rectangle.width = WIDTH;
        this.rectangle.height = HEIGHT;
        this.uuid = UUID.randomUUID();
    }

    public Tank(TankJoinMsg tankJoinMsg) {
        this.x = tankJoinMsg.getX();
        this.y = tankJoinMsg.getY();
        this.dir = tankJoinMsg.getDir();
        this.moving = tankJoinMsg.isMoving();
        this.group = tankJoinMsg.getGroup();
        this.uuid = tankJoinMsg.getUuid();
        this.tankFrame = TankFrame.getInstance();

        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = WIDTH;
        this.rectangle.height = HEIGHT;
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

    public UUID getUuid() {
        return uuid;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        if(!live) {
            tankFrame.tanks.remove(this);
        }

        move();

        switch (dir) {
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            default:break;
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
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

//        if(this.group == Group.BAD && random.nextInt(100) > 95) {
//            this.fire();
//            this.randomDir();
//        }

        boundsCheck();

        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
    }

    private void boundsCheck() {
        if(this.x < 2) {
            this.x = 2;
        }
        if(this.y < 32) {
            this.y = 32;
        }
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            this.x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) {
            this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = x + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int bY = y + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        tankFrame.bulletList.add(new Bullet(bX, bY, dir, this.group, this.getUuid(), tankFrame));
    }

    public void die() {
        this.live = false;
        int eX = this.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
        int eY = this.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
        tankFrame.explodeList.add(new Explode(eX, eY, tankFrame));
        System.out.println(tankFrame.tanks.size());
    }
}
