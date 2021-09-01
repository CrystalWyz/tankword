package cn.wyz.tankword.net.bean;

import cn.wyz.tankword.bean.Tank;
import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.constant.Group;
import cn.wyz.tankword.net.client.TankClient;
import cn.wyz.tankword.ui.TankFrame;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author wnx
 */
public class TankJoinMsg extends BaseMsg {

    private int x;
    private int y;
    private Dir dir;
    private boolean moving;
    private Group group;
    private UUID uuid;

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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public TankJoinMsg(Tank tank) {
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.moving = tank.isMoving();
        this.group = tank.getGroup();
        this.uuid = tank.getUuid();
    }

    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID uuid) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.uuid = uuid;
    }

    @Override
    public void handle() {
        if(this.uuid.equals(TankFrame.getInstance().getMainTank().getUuid()) || TankFrame.getInstance().tanks.get(this.uuid) != null) {
            return;
        }
        Tank tank = new Tank(this);
        TankFrame.getInstance().addTank(tank);

        TankClient.getInstance().sendMsg(new TankJoinMsg(TankFrame.getInstance().getMainTank()));
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            dos.writeLong(uuid.getMostSignificantBits());
            dos.writeLong(uuid.getLeastSignificantBits());
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", group=" + group +
                '}';
    }
}
