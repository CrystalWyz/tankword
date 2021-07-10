package cn.wyz.tankword.factory;

import cn.wyz.tankword.Tank;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);
    public abstract void collideWith(BaseTank tank);
}
