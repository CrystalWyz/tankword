package cn.wyz.tankword.net.bean;

import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.constant.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * @author wnx
 */
public class BaseMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() < 33) {
            return;
        }

        int x = byteBuf.readInt();
        int y = byteBuf.readInt();
        Dir dir = Dir.values()[byteBuf.readInt()];
        boolean moving = byteBuf.readBoolean();
        Group group = Group.values()[byteBuf.readInt()];
        UUID uuid = new UUID(byteBuf.readLong(),byteBuf.readLong());

        list.add(new TankJoinMsg(x, y, dir, moving, group, uuid));
    }
}
