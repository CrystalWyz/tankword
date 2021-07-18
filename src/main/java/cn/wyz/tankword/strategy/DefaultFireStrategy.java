package cn.wyz.tankword.strategy;

import cn.wyz.tankword.bean.Audio;
import cn.wyz.tankword.bean.Bullet;
import cn.wyz.tankword.bean.Tank;
import cn.wyz.tankword.constant.Group;
import cn.wyz.tankword.decorator.RectDecorator;
import cn.wyz.tankword.facade.GameModel;
import cn.wyz.tankword.mgr.ResourceMgr;

/**
 * @author wangnanxiang
 * 单例模式 double check lock
 */
public class DefaultFireStrategy implements FireStrategy {

    private static volatile DefaultFireStrategy defaultFireStrategy;

    public DefaultFireStrategy() {
    }

    public static DefaultFireStrategy getInstance() {
        if(defaultFireStrategy == null) {
            synchronized (DefaultFireStrategy.class) {
                if(defaultFireStrategy == null) {
                    defaultFireStrategy = new DefaultFireStrategy();
                }
            }
        }
        return defaultFireStrategy;
    }

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int bY = tank.getY() + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        GameModel.getInstance().add(new RectDecorator(new Bullet(bX, bY, tank.getDir(), tank.getGroup())));

        if(tank.getGroup() == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
