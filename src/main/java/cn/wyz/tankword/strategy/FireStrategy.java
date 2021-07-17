package cn.wyz.tankword.strategy;

import cn.wyz.tankword.Tank;

@FunctionalInterface
public interface FireStrategy {
    /**
     * 坦克开火
     * @param tank 指定坦克
     */
    void fire(Tank tank);
}
