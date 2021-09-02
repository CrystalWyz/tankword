package cn.wyz.tankword.ui;

import cn.wyz.tankword.bean.Bullet;
import cn.wyz.tankword.bean.Explode;
import cn.wyz.tankword.bean.Tank;
import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.constant.Group;
import cn.wyz.tankword.mgr.PropertiesMgr;
import cn.wyz.tankword.net.bean.TankDirChangedMsg;
import cn.wyz.tankword.net.bean.TankFireMsg;
import cn.wyz.tankword.net.bean.TankStartMovingMsg;
import cn.wyz.tankword.net.bean.TankStopMsg;
import cn.wyz.tankword.net.client.TankClient;

import java.awt.*;
import java.util.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * @author wangnanxiang
 */
public class TankFrame extends Frame {

    public static final int GAME_WIDTH = PropertiesMgr.getInteger("gameWidth");
    public static final int GAME_HEIGHT = PropertiesMgr.getInteger("gameHeight");

    private static final TankFrame INSTANCE = new TankFrame();

    Tank tank = new Tank(new Random().nextInt(800), new Random().nextInt(600) , Dir.UP, Group.GOOD, this);
    public List<Bullet> bulletList = new ArrayList<>();
    public Map<UUID, Tank> tanks = new HashMap<>();
    public List<Explode> explodeList = new ArrayList<>();

    boolean bU = false, bD = false, bL = false, bR = false;

    public void addTank(Tank tank) {
        this.tanks.put(tank.getUuid(), tank);
    }

    public Tank getMainTank() {
        return tank;
    }

    public Tank getTankByUuid(UUID uuid) {
        return tanks.get(uuid);
    }

    public static TankFrame getInstance() {
        return INSTANCE;
    }

    private TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
//        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
        tanks.put(tank.getUuid(), tank);
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

        for(int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        tanks.values().parallelStream().forEach((e) -> {
            if(e.isLive()) {
                e.paint(g);
            }
        });

        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }

        //碰撞检测
        Iterator<Tank> iterator = tanks.values().iterator();
        for (int i = 0; i < bulletList.size(); i++) {
            while (iterator.hasNext()) {
                bulletList.get(i).collideWith(iterator.next());
            }
        }
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
                    TankClient.getInstance().sendMsg(new TankFireMsg(tank));
                    break;
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
        Dir oldDir = tank.getDir();
        if(!bU && !bD && !bL && !bR) {
            tank.setMoving(false);
            TankClient.getInstance().sendMsg(new TankStopMsg(tank));
        } else {

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
            if(!tank.isMoving()) {
                TankClient.getInstance().sendMsg(new TankStartMovingMsg(tank));
            }
            tank.setMoving(true);

            if(oldDir != tank.getDir()) {
                TankClient.getInstance().sendMsg(new TankDirChangedMsg(tank));
            }
        }
    }
}
