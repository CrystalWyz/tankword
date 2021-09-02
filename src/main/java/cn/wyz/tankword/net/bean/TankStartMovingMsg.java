package cn.wyz.tankword.net.bean;

import cn.wyz.tankword.bean.Tank;
import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.net.constant.MsgType;
import cn.wyz.tankword.ui.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @author wnx
 */
public class TankStartMovingMsg extends BaseMsg {

    private UUID uuid;
    private int x;
    private int y;
    private Dir dir;

    public TankStartMovingMsg() {
    }

    public TankStartMovingMsg(UUID uuid, int x, int y, Dir dir) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public TankStartMovingMsg(Tank tank) {
        this.uuid = tank.getUuid();
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    @Override
    public void handle() {
        if (this.uuid.equals(TankFrame.getInstance().getMainTank().getUuid())) {
            return;
        }

        Tank tank = TankFrame.getInstance().getTankByUuid(this.uuid);

        if(tank != null) {
            tank.setMoving(true);
            tank.setX(this.x);
            tank.setY(this.y);
            tank.setDir(this.dir);
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        try{
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(this.uuid.getMostSignificantBits());
            dos.writeLong(this.uuid.getLeastSignificantBits());
            dos.writeInt(this.x);
            dos.writeInt(this.y);
            dos.writeInt(this.dir.ordinal());
            dos.flush();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStartMoving;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));

        try {
            this.uuid = new UUID(dis.readLong(), dis.readLong());
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
