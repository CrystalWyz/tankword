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

    public static final int MSG_HEAD_LENGTH = 8;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() < MSG_HEAD_LENGTH) {
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

        BaseMsg baseMsg = (BaseMsg)Class.forName("cn.wyz.tankword.net.bean." + msgType.toString() + "Msg")
                .getDeclaredConstructor().newInstance();
        baseMsg.parse(bytes);
        list.add(baseMsg);
        /*switch (msgType) {
            case TankJoin:
                TankJoinMsg tankJoinMsg = new TankJoinMsg();
                tankJoinMsg.parse(bytes);
                break;
            default:
        }*/
    }
}
