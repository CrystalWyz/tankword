package cn.wyz.tankword.decorator;

import cn.wyz.tankword.bean.BaseGameObject;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public class BaseGameObjectDecorator extends BaseGameObject {

    protected final BaseGameObject baseGameObject;

    public BaseGameObject getBaseGameObject() {
        return baseGameObject;
    }

    public BaseGameObjectDecorator(BaseGameObject baseGameObject) {
        this.baseGameObject = baseGameObject;
    }

    @Override
    public void paint(Graphics g) {
        baseGameObject.paint(g);
    }
}
