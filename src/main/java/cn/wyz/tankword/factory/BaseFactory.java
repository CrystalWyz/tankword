package cn.wyz.tankword.factory;

import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.constant.Group;
import cn.wyz.tankword.bean.TankFrame;

/**
 * @author wangnanxiang
 */
public abstract class BaseFactory {
    public abstract BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    public abstract BaseExplode creatExplode(int x, int y, TankFrame tankFrame);
    public abstract BaseBullet creatBullet(int x, int y, Dir dir, Group group,TankFrame tankFrame);
}
