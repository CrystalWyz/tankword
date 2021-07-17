package cn.wyz.tankword.cor;

import cn.wyz.tankword.BaseGameObject;
import cn.wyz.tankword.Tank;

/**
 * @author wangnanxiang
 */
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(BaseGameObject bgo1, BaseGameObject bgo2) {
        if (bgo1 == bgo2) {
            return true;
        }

        if(bgo1 instanceof Tank && bgo2 instanceof Tank) {
            Tank tank1 = (Tank) bgo1;
            Tank tank2 = (Tank) bgo2;

            if(tank1.getRectangle().intersects(tank2.getRectangle())) {
                tank1.back();
                tank2.back();

                tank1.randomDir();
                tank2.randomDir();
            }
            return true;
        }
        return false;
    }
}
