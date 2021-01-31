package com.jk.week_02.nettyServe.filter.impl;

import com.jk.week_02.nettyServe.filter.HttpResponseFilter;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author wei.huang
 * @version Id: HeaderHttpResponseFilter.java, v 0.1 2021年01月26日  10:38 wei.huang Exp $
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().add("name","lisi");
    }
}
