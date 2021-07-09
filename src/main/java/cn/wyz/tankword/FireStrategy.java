package cn.wyz.tankword;

@FunctionalInterface
public interface FireStrategy {
    /**
     * 坦克开火
     * @param tank 指定坦克
     */
    void fire(Tank tank);
}
