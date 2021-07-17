package cn.wyz.tankword.cor;

import cn.wyz.tankword.BaseGameObject;

/**
 * @author wangnanxiang
 */
public interface Collider {

    /**
     * @param bgo1 碰撞对象1
     * @param bgo2  碰撞对象2
     * @return 是否处理成功
     */
    boolean collide(BaseGameObject bgo1, BaseGameObject bgo2);
}
