package cn.wyz.tankword;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    Tank tank = new Tank(200, 200 , Dir.UP, this);
    Bullet bullet = new Bullet(300,300,Dir.UP);

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
        tank.paint(g);
        bullet.paint(g);
    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
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
                    tank.fire();
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
