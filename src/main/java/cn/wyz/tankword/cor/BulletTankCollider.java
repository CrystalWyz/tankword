package cn.wyz.tankword.cor;

import cn.wyz.tankword.BaseGameObject;
import cn.wyz.tankword.Bullet;
import cn.wyz.tankword.Tank;

/**
 * @author wangnanxiang
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(BaseGameObject bgo1, BaseGameObject bgo2) {
        if (bgo1 == bgo2) {
            return true;
        }
        if (bgo1 instanceof Bullet && bgo2 instanceof Tank) {
            Bullet bullet = (Bullet) bgo1;
            Tank tank = (Tank) bgo2;

            if(bullet.getGroup() == tank.getGroup()) {
                return true;
            }
            if(bullet.getRectangle().intersects(tank.getRectangle())) {
                tank.die();
                bullet.die();
            }
            return true;
        } else if (bgo1 instanceof Tank && bgo2 instanceof Bullet) {
            return collide(bgo2, bgo1);
        }
        return false;
    }
}
