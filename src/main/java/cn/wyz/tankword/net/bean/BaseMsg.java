package cn.wyz.tankword.net.bean;

public abstract class BaseMsg {
    public abstract void handle();
    public abstract byte[] toBytes();
}
