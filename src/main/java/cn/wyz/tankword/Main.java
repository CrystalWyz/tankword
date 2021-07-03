package cn.wyz.tankword;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        //初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(i * 60, 100, Dir.DOWN, Group.BAD, tankFrame));
        }

        //背景音效
        //new  Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
