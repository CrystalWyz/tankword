package cn.wyz.tankword.bean;

import cn.wyz.tankword.constant.Group;
import cn.wyz.tankword.mgr.ResourceMgr;
import cn.wyz.tankword.net.bean.TankDieMsg;
import cn.wyz.tankword.net.client.TankClient;
import cn.wyz.tankword.ui.TankFrame;
import cn.wyz.tankword.constant.Dir;

import java.awt.*;
import java.util.Iterator;
import java.util.UUID;

public class Bullet {
    private static final int SPEED = 15;
    public static final int WIDTH = ResourceMgr.bulletU.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletU.getHeight();
    private Boolean live = true;
    private int x, y;
    private Dir dir;
    private final TankFrame tankFrame;
    private Group group = Group.BAD;
    private Rectangle rectangle = new Rectangle();
    private UUID uuid;

    public Bullet(int x, int y, Dir dir, Group group, UUID uuid, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        this.uuid = uuid;

        this.rectangle.x = this.x;
        this.rectangle.y = y;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void paint(Graphics g) {
        if(!live) {
            tankFrame.bulletList.remove(this);
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

    public void collideWith(Tank tank) {
        /*if(this.getGroup() == tank.getGroup()) {
            return;
        }*/
        if(this.uuid.equals(tank.getUuid())) {
            return;
        }
        if(this.live && tank.live && this.rectangle.intersects(tank.getRectangle())) {
            tank.die();
            this.die();
            TankClient.getInstance().sendMsg(new TankDieMsg(tank));
        }
    }

    private void die() {
        live = false;
    }
}
