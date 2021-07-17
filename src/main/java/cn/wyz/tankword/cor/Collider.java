package cn.wyz.tankword.cor;

import cn.wyz.tankword.BaseGameObject;

/**
 * @author wangnanxiang
 */
public interface Collider {
    /**
     * 碰撞检测
     * @param bgo1 碰撞对象1
     * @param bgo2  碰撞对象2
     */
    boolean collide(BaseGameObject bgo1, BaseGameObject bgo2);
}
