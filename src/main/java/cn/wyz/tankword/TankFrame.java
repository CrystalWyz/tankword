package cn.wyz.tankword;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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

    Tank tank = new Tank(200, 600 , Dir.UP, Group.GOOD, this);
    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodeList = new ArrayList<>();

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
        Color color = gOffScreen.getColor();;
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bulletList.size(), 50, 50);
        g.drawString("敌人的数量:" + tanks.size(), 50, 80);
        g.drawString("爆炸的数量:" + tanks.size(), 50, 110);
        g.setColor(color);

        tank.paint(g);
        for(int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        for(int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }

        //碰撞检测
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bulletList.get(i).collideWith(tanks.get(j));
            }
        }
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
                        tank.fire((FireStrategy) getInstance.invoke(null));
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
            tank.setMoving(false);
        } else {
            tank.setMoving(true);
            if(bU) {
                tank.setDir(Dir.UP);
            }
            if(bD) {
                tank.setDir(Dir.DOWN);
            }
            if(bL) {
                tank.setDir(Dir.LEFT);
            }
            if(bR) {
                tank.setDir(Dir.RIGHT);
            }
        }
    }
}
