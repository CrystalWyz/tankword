package cn.wyz.tankword.mgr;

import cn.wyz.tankword.utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author wangnanxiang
 */
public class ResourceMgr {
    public static BufferedImage goodTankU, goodTankD, goodTankL, goodTankR;
    public static BufferedImage badTankU, badTankD, badTankL, badTankR;
    public static BufferedImage bulletU, bulletD, bulletL,bulletR;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            goodTankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png")));
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);

            badTankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png")));
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);

            bulletU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png")));
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);

            for (int i = 0; i < 16; i++) {
                InputStream is;
                explodes[i] = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
