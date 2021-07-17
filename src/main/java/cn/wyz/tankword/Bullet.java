package cn.wyz.tankword;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public class Bullet extends BaseGameObject {
    private static final int SPEED = 15;
    public static final int WIDTH = ResourceMgr.bulletU.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletU.getHeight();
    private Boolean live = true;
    private Dir dir;
    private final GameModel gameModel;
    private Group group = Group.BAD;
    private final Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;

        this.rectangle.x = this.x;
        this.rectangle.y = y;
        this.rectangle.width = WIDTH;
        this.rectangle.height = HEIGHT;

        this.gameModel.getBaseGameObjectList().add(this);
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void paint(Graphics g) {
        if(!live) {
            gameModel.getBaseGameObjectList().remove(this);
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
        if(this.getGroup() == tank.getGroup()) {
            return;
        }
        if(this.rectangle.intersects(tank.getRectangle())) {
            tank.die();
            this.die();
        }
    }

    public void die() {
        live = false;
    }
}
