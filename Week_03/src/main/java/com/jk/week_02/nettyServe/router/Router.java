package com.jk.week_02.nettyServe.router;

import java.util.List;

/**
 * @author wei.huang
 * @version Id: Router.java, v 0.1 2021年01月26日  10:53 wei.huang Exp $
 */
public interface Router {
    String router(List<String> servers);
}
