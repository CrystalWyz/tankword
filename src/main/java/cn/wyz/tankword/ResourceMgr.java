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
            tankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif")));
            tankD = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif")));
            tankL = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif")));
            tankR = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif")));

            bulletU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif")));
            bulletD = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif")));
            bulletL = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif")));
            bulletR = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif")));

            for (int i = 0; i < 16; i++) {
                InputStream is;
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
