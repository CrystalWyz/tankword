package cn.wyz.tankword.bean;

import cn.wyz.tankword.constant.Group;
import cn.wyz.tankword.Mgr.PropertiesMgr;
import cn.wyz.tankword.Mgr.ResourceMgr;
import cn.wyz.tankword.Strategy.FireStrategy;
import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.factory.BaseTank;

import java.awt.*;
import java.lang.reflect.Method;

/**
 * @author wangnanxiang
 */
public class Tank extends BaseTank {
    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        super(tankFrame);
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        this.rectangle.x = this.x;
        this.rectangle.y = y;
        this.rectangle.width = WIDTH;
        this.rectangle.height = HEIGHT;

        if(group == Group.GOOD) {
            moving = false;
        }
    }

    @Override
    public void paint(Graphics g) {
        if(!live) {
            tankFrame.getTanks().remove(this);
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

    @Override
    public void move() {
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

    @Override
    public void boundsCheck() {
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

    @Override
    public void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    @Override
    public void fire(FireStrategy fs) {
        fs.fire(this);
    }

    @Override
    public void die() {
        this.live = false;
        int eX = this.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
        int eY = this.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
        tankFrame.getExplodeList().add(tankFrame.getBaseFactory().creatExplode(eX, eY, tankFrame));
    }
}
