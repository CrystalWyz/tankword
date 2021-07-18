package cn.wyz.tankword.view;

import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.facade.GameModel;
import cn.wyz.tankword.mgr.PropertiesMgr;
import cn.wyz.tankword.strategy.FireStrategy;

import java.awt.*;
import java.lang.reflect.Method;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author wangnanxiang
 */
public class TankFrame extends Frame {
    public static final int GAME_WIDTH = PropertiesMgr.getInteger("gameWidth");
    public static final int GAME_HEIGHT = PropertiesMgr.getInteger("gameHeight");

    boolean bU = false, bD = false, bL = false, bR = false;
    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        GameModel.getInstance().paint(g);
    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_SPACE:
                    try {
                        Class<?> clazz = Class.forName(PropertiesMgr.getString("goodTank"));
                        Method getInstance = clazz.getDeclaredMethod("getInstance");
                        GameModel.getInstance().getMainTank().fire((FireStrategy) getInstance.invoke(null));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                default:break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                default:break;
            }
            setMainTankDir();
        }


    }

    private void setMainTankDir() {
        if(!bU && !bD && !bL && !bR) {
            GameModel.getInstance().getMainTank().setMoving(false);
        } else {
            GameModel.getInstance().getMainTank().setMoving(true);
            if(bU) {
                GameModel.getInstance().getMainTank().setDir(Dir.UP);
            }
            if(bD) {
                GameModel.getInstance().getMainTank().setDir(Dir.DOWN);
            }
            if(bL) {
                GameModel.getInstance().getMainTank().setDir(Dir.LEFT);
            }
            if(bR) {
                GameModel.getInstance().getMainTank().setDir(Dir.RIGHT);
            }
        }
    }
}
