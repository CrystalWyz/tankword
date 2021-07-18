package cn.wyz.tankword.mgr;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author wangnanxiang
 * 单例模式 简单饿汉式
 */
public class PropertiesMgr {
    static Properties properties = new Properties();

    private PropertiesMgr() {
    }

    public static Properties getInstance() {
        return properties;
    }

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
