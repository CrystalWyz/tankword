package cn.wyz.tankword;

import cn.wyz.tankword.Mgr.PropertiesMgr;
import cn.wyz.tankword.bean.TankFrame;
import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.bean.Tank;
import cn.wyz.tankword.constant.Group;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int initTankCount = PropertiesMgr.getInteger("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.getTanks().add(new Tank(i * 60, 100, Dir.DOWN, Group.BAD, tankFrame));
        }

        //背景音效
        //new  Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
