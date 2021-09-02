package cn.wyz.tankword.net.constant;

/**
 * @author wnx
 */

public enum MsgType {
    /*
       坦克加入类型
     */
    TankJoin,
    /*
       坦克方向改变类型
     */
    TankDirChanged,
    /*
       坦克停止类型
     */
    TankStop,
    /*
       坦克开始移动类型
     */
    TankStartMoving,
    /*
       坦克发射类型
     */
    TankFire,
    /*
       坦克死亡类型
     */
    TankDie
}
