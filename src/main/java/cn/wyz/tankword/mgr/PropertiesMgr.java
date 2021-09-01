package cn.wyz.tankword.mgr;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author wangnanxiang
 */
public class PropertiesMgr {
    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(Object key) {
        if(properties == null) {
            return null;
        }
        return properties.get(key);
    }

    public static Integer getInteger(Object key) {
        return Integer.parseInt((String) Objects.requireNonNull(get(key)));
    }

    public static String getString(Object key) {
        return (String) Objects.requireNonNull(get(key));
    }
}
