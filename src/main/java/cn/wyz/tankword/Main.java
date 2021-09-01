package cn.wyz.tankword;

import cn.wyz.tankword.net.client.TankClient;
import cn.wyz.tankword.ui.TankFrame;

/**
 * @author wnx
 */
public class Main {
    public static void main(String[] args) throws Exception {
        TankFrame tankFrame = TankFrame.getInstance();
        tankFrame.setVisible(true);
        /*int initTankCount = PropertiesMgr.getInteger("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.tanks.add(new Tank(i * 60, 100, Dir.DOWN, Group.BAD, tankFrame));
        }*/

        //背景音效
        //new  Thread(() -> new Audio("audio/war1.wav").loop()).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tankFrame.repaint();
            }
        }).start();

        TankClient.getInstance().connect();
    }
}
