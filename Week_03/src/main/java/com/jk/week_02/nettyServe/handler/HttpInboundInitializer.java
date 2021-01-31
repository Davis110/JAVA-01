package com.jk.week_02.nettyServe.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.proxy.HttpProxyHandler;

import java.util.List;

/**
 * @author wei.huang
 * @version Id: NettyChannelHandler.java, v 0.1 2021年01月26日  10:05 wei.huang Exp $
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private List<String> servers;

    public HttpInboundInitializer(List<String> servers) {
        this.servers = servers;
    }

    @Override protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline p = sc.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new HttpInboundHandler(servers));
    }
}
