package cn.wyz.tankword;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangnanxiang
 */
public class GameModel {

    private List<Bullet> bulletList = new ArrayList<>();
    private List<Tank> tanks = new ArrayList<>();
    private List<Explode> explodeList = new ArrayList<>();
    private Tank tank = new Tank(200, 600 , Dir.UP, Group.GOOD, this);

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }

    public List<Explode> getExplodeList() {
        return explodeList;
    }

    public void setExplodeList(List<Explode> explodeList) {
        this.explodeList = explodeList;
    }

    public GameModel() {
        int initTankCount = PropertiesMgr.getInteger("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(i * 60, 100, Dir.DOWN, Group.BAD, this));
        }
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bulletList.size(), 50, 50);
        g.drawString("敌人的数量:" + tanks.size(), 50, 80);
        g.drawString("爆炸的数量:" + tanks.size(), 50, 110);
        g.setColor(color);

        tank.paint(g);
        for(int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        for(int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }

        //碰撞检测
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bulletList.get(i).collideWith(tanks.get(j));
            }
        }
    }

    public Tank getMainTank() {
        return tank;
    }
}
