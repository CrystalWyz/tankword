package cn.wyz.tankword;

import cn.wyz.tankword.strategy.FireStrategy;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * @author wangnanxiang
 */
public class Tank extends BaseGameObject {
    private int lastX, lastY;
    private Dir dir;
    private static final int SPEED = 3;
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
    private Group group = Group.BAD;
    public boolean live = true;
    private boolean moving = true;
    private final Random random = new Random();
    private final Rectangle rectangle = new Rectangle();

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.lastX = x;
        this.lastY = y;
        this.dir = dir;
        this.group = group;
        if(group == Group.GOOD) {
            moving = false;
        }

        this.rectangle.x = this.x;
        this.rectangle.y = y;
        this.rectangle.width = WIDTH;
        this.rectangle.height = HEIGHT;

        GameModel.getInstance().getBaseGameObjectList().add(this);
    }

    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
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

    @Override
    public void paint(Graphics g) {
        if(!live) {
            GameModel.getInstance().getBaseGameObjectList().remove(this);
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
                lastY = y;
                y -= SPEED;
                break;
            case DOWN:
                lastY = y;
                y += SPEED;
                break;
            case LEFT:
                lastX = x;
                x -= SPEED;
                break;
            case RIGHT:
                lastX = x;
                x += SPEED;
                break;
            default:break;
        }

        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            String badTank = PropertiesMgr.getString("badTank");
            try {
                //读取配置获取策略名
                Class<?> clazz = Class.forName(badTank);
                //得到策略实现类的getInstance()方法
                Method getInstance = clazz.getDeclaredMethod("getInstance");
                //invoke执行该方法
                this.fire((FireStrategy) getInstance.invoke(null));
                this.randomDir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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

    public void back() {
        this.x = lastX;
        this.y = lastY;
    }

    public void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire(FireStrategy fs) {
        fs.fire(this);
    }

    public void die() {
        this.live = false;
        int eX = this.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
        int eY = this.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
        GameModel.getInstance().getBaseGameObjectList().add(new Explode(eX, eY));
    }
}
