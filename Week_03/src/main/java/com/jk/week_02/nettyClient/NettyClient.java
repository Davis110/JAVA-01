package com.jk.week_02.nettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Constant;

import java.io.UnsupportedEncodingException;

/**
 * @author wei.huang
 * @version Id: NettyClient.java, v 0.1 2021年01月31日  9:58 wei.huang Exp $
 */
public class NettyClient {

    private static class SingletonHolder {
        static final NettyClient instance = new NettyClient();
    }

    public static NettyClient getInstance() {
        return SingletonHolder.instance;
    }

    private ChannelFuture  cf;
    private Bootstrap      bs;
    private Channel        channel;
    private EventLoopGroup eventLoopGroup;
    private ClientHandler  clientHandler = new ClientHandler();

    private NettyClient() {
        eventLoopGroup = new NioEventLoopGroup();
        bs = new Bootstrap();
        bs.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(clientHandler);
                    }
                });
    }

    public ChannelFuture connect(String ip, int port) {
        try {
            if (this.cf == null) {
                this.cf = bs.connect(ip, port).sync();
                System.out.println("远程服务器已经连接, 可以进行数据交换..");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        return this.cf;
    }

    public String sendMsg(String requestMsg) throws UnsupportedEncodingException {
        channel = cf.channel();
        channel.writeAndFlush(Unpooled.copiedBuffer(requestMsg.getBytes("UTF-8")));
        return clientHandler.getResult();
    }

    public void closeConnect() {
        try {
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
