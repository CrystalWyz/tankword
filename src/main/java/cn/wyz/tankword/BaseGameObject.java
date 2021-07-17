package cn.wyz.tankword;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public abstract class BaseGameObject {
    private int x, y;

    /**
     * 绘画方法
     * @param g 画笔
     */
    public abstract void paint(Graphics g);
}
