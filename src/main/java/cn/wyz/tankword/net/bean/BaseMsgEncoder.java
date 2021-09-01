package cn.wyz.tankword.net.bean;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wnx
 */
public class BaseMsgEncoder extends MessageToByteEncoder<BaseMsg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(baseMsg.getMsgType().ordinal());
        byteBuf.writeInt(baseMsg.toBytes().length);
        byteBuf.writeBytes(baseMsg.toBytes());
    }
}
