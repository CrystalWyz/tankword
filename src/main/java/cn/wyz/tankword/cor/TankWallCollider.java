package cn.wyz.tankword.cor;

import cn.wyz.tankword.*;

/**
 * @author wangnanxiang
 */
public class TankWallCollider implements Collider {
    @Override
    public boolean collide(BaseGameObject bgo1, BaseGameObject bgo2) {
        if (bgo1 == bgo2) {
            return true;
        }
        if (bgo1 instanceof Tank && bgo2 instanceof Wall) {
            Tank tank = (Tank) bgo1;
            Wall wall = (Wall) bgo2;

            if(tank.getRectangle().intersects(wall.getRectangle())) {
                tank.back();
                if(tank.getGroup() == Group.BAD) {
                    tank.randomDir();
                }
            }
            return true;
        } else if (bgo1 instanceof Wall && bgo2 instanceof Tank) {
            return collide(bgo2, bgo1);
        }
        return false;
    }
}
