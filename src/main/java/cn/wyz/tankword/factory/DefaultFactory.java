package cn.wyz.tankword.factory;

import cn.wyz.tankword.*;

public class DefaultFactory extends BaseFactory {

    @Override
    public BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new Tank(x, y, dir, group, tankFrame);
    }

    @Override
    public BaseExplode creatExplode(int x, int y, TankFrame tankFrame) {
        return new Explode(x, y, tankFrame);
    }

    @Override
    public BaseBullet creatBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new Bullet(x, y, dir, group, tankFrame);
    }
}