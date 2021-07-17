package cn.wyz.tankword.cor;

import cn.wyz.tankword.BaseGameObject;
import cn.wyz.tankword.Bullet;
import cn.wyz.tankword.Tank;
import cn.wyz.tankword.Wall;

/**
 * @author wangnanxiang
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(BaseGameObject bgo1, BaseGameObject bgo2) {
        if (bgo1 == bgo2) {
            return true;
        }
        if (bgo1 instanceof Bullet && bgo2 instanceof Wall) {
            Bullet bullet = (Bullet) bgo1;
            Wall wall = (Wall) bgo2;

            if(bullet.getRectangle().intersects(wall.getRectangle())) {
                bullet.die();
                return false;
            }
            return true;
        } else if (bgo1 instanceof Wall && bgo2 instanceof Bullet) {
            return collide(bgo2, bgo1);
        }
        return false;
    }
}
