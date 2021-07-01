package cn.wyz.tankword;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(i * 60, 100, Dir.DOWN, tankFrame));
        }

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
