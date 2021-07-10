package cn.wyz.tankword;

/**
 * @author wangnanxiang
 * 单例模式 double check lock
 */
public class DefaultFireStrategy implements FireStrategy{

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
        tank.getTankFrame().getBaseFactory().creatBullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getTankFrame());

        if(tank.getGroup() == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
