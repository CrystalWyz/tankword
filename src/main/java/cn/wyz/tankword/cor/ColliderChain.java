package cn.wyz.tankword.cor;

import cn.wyz.tankword.BaseGameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wangnanxiang
 */
public class ColliderChain implements Collider {
    private final List<Collider> colliderChain = new LinkedList<>();

    public ColliderChain() {
        this.add(new BulletTankCollider());
        this.add(new TankTankCollider());
    }

    public void add(Collider collider) {
        this.colliderChain.add(collider);
    }

    @Override
    public boolean collide(BaseGameObject bgo1, BaseGameObject bgo2) {
        for (int i = 0; i < colliderChain.size(); i++) {
            if(colliderChain.get(i).collide(bgo1, bgo2)){
                return true;
            };
        }
        return false;
    }
}
