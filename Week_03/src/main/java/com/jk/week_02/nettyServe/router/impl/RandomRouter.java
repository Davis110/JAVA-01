package com.jk.week_02.nettyServe.router.impl;

import com.jk.week_02.nettyServe.router.Router;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author wei.huang
 * @version Id: RandomRouter.java, v 0.1 2021年01月26日  10:54 wei.huang Exp $
 */
public class RandomRouter implements Router {
    @Override public String router(List<String> servers) {
        // 随机算法
        int i =  new Random().nextInt(servers.size());
        return servers.get(i);
    }
}
