package cn.wyz.tankword;

import cn.wyz.tankword.cor.BulletTankCollider;
import cn.wyz.tankword.cor.Collider;
import cn.wyz.tankword.cor.ColliderChain;
import cn.wyz.tankword.cor.TankTankCollider;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangnanxiang
 */
public class GameModel {

//    private List<Bullet> bulletList = new ArrayList<>();
//    private List<Tank> tanks = new ArrayList<>();
//    private List<Explode> explodeList = new ArrayList<>();
    private List<BaseGameObject> baseGameObjectList = new ArrayList<>();
    private final Tank tank = new Tank(200, 600 , Dir.UP, Group.GOOD, this);
//    private final Collider bulletTankCollider = new BulletTankCollider();
//    private final Collider tankTankCollider = new TankTankCollider();
    private final ColliderChain colliderChain = new ColliderChain();

    public List<BaseGameObject> getBaseGameObjectList() {
        return baseGameObjectList;
    }

    public void setBaseGameObjectList(List<BaseGameObject> baseGameObjectList) {
        this.baseGameObjectList = baseGameObjectList;
    }

    public void add(BaseGameObject baseGameObject) {
        this.baseGameObjectList.add(baseGameObject);
    }

    public void remove(BaseGameObject baseGameObject){
        this.baseGameObjectList.remove(baseGameObject);
    }

    public GameModel() {
        int initTankCount = PropertiesMgr.getInteger("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            this.add(new Tank(i * 60, 100, Dir.DOWN, Group.BAD, this));
        }
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量:" + bulletList.size(), 50, 50);
//        g.drawString("敌人的数量:" + tanks.size(), 50, 80);
//        g.drawString("爆炸的数量:" + tanks.size(), 50, 110);
        g.setColor(color);

        tank.paint(g);
        for(int i = 0; i < baseGameObjectList.size(); i++) {
            baseGameObjectList.get(i).paint(g);
        }

        //碰撞检测
        for (int i = 0; i < baseGameObjectList.size(); i++) {
            for (int j = 0; j < baseGameObjectList.size(); j++) {
                BaseGameObject bgo1 = baseGameObjectList.get(i);
                BaseGameObject bgo2 = baseGameObjectList.get(j);
                colliderChain.collide(bgo1, bgo2);
            }
        }
    }

    public Tank getMainTank() {
        return tank;
    }
}
