package cn.wyz.tankword;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author wangnanxiang
 */
public class ResourceMgr {
    public static BufferedImage tankU, tankD, tankL,tankR;
    public static BufferedImage bulletU, bulletD, bulletL,bulletR;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png")));
            tankD = ImageUtil.rotateImage(tankU, 180);
            tankL = ImageUtil.rotateImage(tankU, -90);
            tankR = ImageUtil.rotateImage(tankU, 90);

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
