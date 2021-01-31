package com.jk.week_02.nettyServe.handler;

import com.jk.week_02.HttpClientUtils;
import com.jk.week_02.OkHttpClientUtil;
import com.jk.week_02.nettyClient.NettyClient;
import com.jk.week_02.nettyServe.filter.HttpRequestFilter;
import com.jk.week_02.nettyServe.filter.HttpResponseFilter;
import com.jk.week_02.nettyServe.filter.impl.HeaderHttpResponseFilter;
import com.jk.week_02.nettyServe.router.Router;
import com.jk.week_02.nettyServe.router.impl.RandomRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author wei.huang
 * @version Id: HttpOutboundHandler.java, v 0.1 2021年01月26日  10:23 wei.huang Exp $
 */
public class HttpOutboundHandler {

    private List<String>       servers;
    private Router             router = new RandomRouter();
    private HttpResponseFilter filter = new HeaderHttpResponseFilter();
    private ExecutorService    proxyService;

    public HttpOutboundHandler(List<String> servers) {
        this.servers = servers.stream().map(this::formatUrl).collect(Collectors.toList());
        // 设置线程池
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maximumPoolSize = corePoolSize * 2;
        long keepAliveTime = 2000;
        int queueSize = 500;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize), handler);
    }

    public void handel(final ChannelHandlerContext ctx, final FullHttpRequest request) {
        //1、 开启多线程-使用okHttpClient将请求转发到后端服务
        // 根据地址请求服务端
        //String url = router.router(servers) + request.getUri();
        //proxyService.submit(() -> fetchGet(request, ctx, url));
        //2、 开启多线程-使用nettyClient将请求转发到后端服务
        netttyPost(request, ctx, "127.0.0.1", 8801);
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/") ? backend.substring(0, backend.length() - 1) : backend;
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("mao", inbound.headers().get("mao"));
        // 使用OKHttpClient 请求服务端
        String result = OkHttpClientUtil.get(url);
        // 请求结果处理
        handleResponse(inbound, ctx, result);
    }

    private String netttyPost(final FullHttpRequest inbound, final ChannelHandlerContext ctx, String ip, int port) {
        String requestMsg = "你好，我是nettyClient Send Msg!";
        String result = "";
        try {
            NettyClient nc = NettyClient.getInstance();
            nc.connect("127.0.0.1", 8801);
            result = nc.sendMsg(requestMsg);
            // 请求结果处理
            handleResponse(inbound, ctx, result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final String responseResult) {
        FullHttpResponse response = null;
        try {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(responseResult.getBytes("UTF-8")));
            //response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            filter.filter(response);
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, OK);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
