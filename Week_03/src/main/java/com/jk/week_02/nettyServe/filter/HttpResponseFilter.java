package com.jk.week_02.nettyServe.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author wei.huang
 * @version Id: HttpResponseFilter.java, v 0.1 2021年01月26日  10:36 wei.huang Exp $
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse response);
}
