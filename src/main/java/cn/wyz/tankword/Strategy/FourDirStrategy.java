package cn.wyz.tankword.Strategy;

import cn.wyz.tankword.Mgr.ResourceMgr;
import cn.wyz.tankword.bean.Audio;
import cn.wyz.tankword.bean.Bullet;
import cn.wyz.tankword.bean.Tank;
import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.constant.Group;

/**
 * @author wangnanxiang
 * 单例模式 double check lock
 */
public class FourDirStrategy implements FireStrategy {
    private static volatile FourDirStrategy fourDirStrategy;

    public FourDirStrategy() {
    }

    public static FourDirStrategy getInstance() {
        if(fourDirStrategy == null) {
            synchronized (FourDirStrategy.class) {
                if(fourDirStrategy == null) {
                    fourDirStrategy = new FourDirStrategy();
                }
            }
        }
        return fourDirStrategy;
    }

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int bY = tank.getY() + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bX, bY, dir, tank.getGroup(), tank.getTankFrame());
        }

        if(tank.getGroup() == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
