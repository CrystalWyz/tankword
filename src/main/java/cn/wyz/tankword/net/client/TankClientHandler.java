package cn.wyz.tankword.net.client;

import cn.wyz.tankword.net.bean.BaseMsg;
import cn.wyz.tankword.net.bean.TankJoinMsg;
import cn.wyz.tankword.ui.TankFrame;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author wnx
 */
public class TankClientHandler extends SimpleChannelInboundHandler<BaseMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        System.out.println(baseMsg);
        baseMsg.handle();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.getInstance().getMainTank()));
    }
}
