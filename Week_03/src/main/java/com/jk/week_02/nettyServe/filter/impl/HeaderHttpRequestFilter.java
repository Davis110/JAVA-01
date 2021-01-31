package com.jk.week_02.nettyServe.filter.impl;

import com.jk.week_02.nettyServe.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author wei.huang
 * @version Id: HeaderHttpRequestFilter.java, v 0.1 2021年01月26日  10:35 wei.huang Exp $
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().add("name","zhangsan");
    }
}
