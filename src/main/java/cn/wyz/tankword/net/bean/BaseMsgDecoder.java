package cn.wyz.tankword.net.bean;

import cn.wyz.tankword.constant.Dir;
import cn.wyz.tankword.constant.Group;
import cn.wyz.tankword.net.constant.MsgType;
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
        if(byteBuf.readableBytes() < 8 ) {
            return;
        }

        byteBuf.markReaderIndex();

        MsgType msgType = MsgType.values()[byteBuf.readInt()];
        int length = byteBuf.readInt();

        if(byteBuf.readableBytes() < length) {
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        switch (msgType) {
            case TankJoin:
                TankJoinMsg tankJoinMsg = new TankJoinMsg();
                tankJoinMsg.parse(bytes);
                break;
            default:
        }
    }
}
