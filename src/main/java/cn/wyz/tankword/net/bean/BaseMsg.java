package cn.wyz.tankword.net.bean;

import cn.wyz.tankword.net.constant.MsgType;

public abstract class BaseMsg {
    public abstract void handle();
    public abstract byte[] toBytes();
    public abstract MsgType getMsgType();
    public abstract void parse(byte[] bytes);
}
