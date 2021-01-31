package com.jk.week_02.nettyServe.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author wei.huang
 * @version Id: HttpRequestFilter.java, v 0.1 2021年01月26日  10:34 wei.huang Exp $
 */
public interface HttpRequestFilter {
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}
