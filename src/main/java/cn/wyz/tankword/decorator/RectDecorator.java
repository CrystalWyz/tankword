package cn.wyz.tankword.decorator;

import cn.wyz.tankword.bean.BaseGameObject;

import java.awt.*;

/**
 * @author wangnanxiang
 */
public class RectDecorator extends BaseGameObjectDecorator {

    public RectDecorator(BaseGameObject baseGameObject) {
        super(baseGameObject);
    }

    @Override
    public void paint(Graphics g) {
        this.x = baseGameObject.getX();
        this.y = baseGameObject.getY();

        super.paint(g);

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(this.getX(), this.getY(),
                this.getBaseGameObject().getWidth() + 2, this.getBaseGameObject().getHeight() + 2);
        g.setColor(color);
    }
}
