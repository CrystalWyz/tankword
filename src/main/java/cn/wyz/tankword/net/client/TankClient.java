package cn.wyz.tankword.net.client;

import cn.wyz.tankword.net.bean.BaseMsg;
import cn.wyz.tankword.net.bean.BaseMsgEncoder;
import cn.wyz.tankword.net.bean.BaseMsgDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wnx
 */
public class TankClient {

    private static final TankClient INSTANCE = new TankClient();
    private Channel channel;

    private TankClient() {
    }

    public static TankClient getInstance() {
        return INSTANCE;
    }

    public void connect() {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture channelFuture = bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new BaseMsgEncoder())
                                    .addLast(new BaseMsgDecoder())
                                    .addLast(new TankClientHandler());
                        }
                    })
                    .connect("localhost", 8080);

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        System.out.println("not connected!");
                    } else {
                        System.out.println("connected!");
                        // initialize the channel
                        channel = future.channel();
                    }
                }
            });

            channelFuture.sync();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }

    public void sendMsg(BaseMsg baseMsg) {
        channel.writeAndFlush(baseMsg);
    }
}
