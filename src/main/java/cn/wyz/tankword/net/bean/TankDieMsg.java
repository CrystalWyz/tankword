package cn.wyz.tankword.net.bean;

import cn.wyz.tankword.bean.Tank;
import cn.wyz.tankword.net.constant.MsgType;
import cn.wyz.tankword.ui.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @author wnx
 */
public class TankDieMsg extends BaseMsg{

    private UUID uuid;

    public TankDieMsg() {
    }

    public TankDieMsg(UUID uuid) {
        this.uuid = uuid;
    }

    public TankDieMsg(Tank tank) {
        this.uuid = tank.getUuid();
    }

    @Override
    public void handle() {
        if(this.uuid.equals(TankFrame.getInstance().getMainTank().getUuid())) {
            return;
        }
        Tank tank = TankFrame.getInstance().getTankByUuid(this.uuid);
        if(tank != null) {
            tank.die();
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(this.uuid.getMostSignificantBits());
            dos.writeLong(this.uuid.getLeastSignificantBits());
        } catch (Exception e) {
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
        return MsgType.TankDie;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.uuid = new UUID(dis.readLong(), dis.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
