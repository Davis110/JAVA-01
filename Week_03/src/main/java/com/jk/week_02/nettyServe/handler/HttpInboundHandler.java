package com.jk.week_02.nettyServe.handler;

import com.jk.week_02.nettyServe.filter.HttpRequestFilter;
import com.jk.week_02.nettyServe.filter.impl.HeaderHttpRequestFilter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author wei.huang
 * @version Id: HttpInboundHandler.java, v 0.1 2021年01月26日  10:13 wei.huang Exp $
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private static Logger              logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private        List<String>        servers;
    private        HttpOutboundHandler httpOutboundHandler;
    private        HttpRequestFilter   filter = new HeaderHttpRequestFilter();

    public HttpInboundHandler(List<String> servers) {
        this.servers = servers;
        this.httpOutboundHandler = new HttpOutboundHandler(this.servers);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try{
            FullHttpRequest request = (FullHttpRequest) msg;
            System.out.println("请求开始时间：" + System.currentTimeMillis() + ";请求参数：" + request.toString());
            String uri = request.uri();
            //logger.info("接收到的请求url为{}", uri);
            if (uri.contains("/test")) {
                handleTest(request, ctx);
            }
            // 请求过滤器
            filter.filter(request,ctx);
            // 处理请求结果
            httpOutboundHandler.handel(ctx, request);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    public void handleTest(FullHttpRequest request, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            filter.filter(request,ctx);

            String value = "这个世界真美好";
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            logger.error("处理测试接口出错", e);
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (request != null) {
                if (!HttpUtil.isKeepAlive(request)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}
