package com.jk.week_02.nettyServe;

import java.util.Arrays;

/**
 * @author wei.huang
 * @version Id: NettyHttpServe.java, v 0.1 2021年01月26日  9:44 wei.huang Exp $
 */
public class NettyHttpServeApplication {

    public static void main(String[] args) {
         String proxyPort = "8881";
        // 这是多个后端url走随机路由的例子
        String proxyServers = System.getProperty("proxyServers","http://localhost:8801,http://localhost:8802");
        int port = Integer.parseInt(proxyPort);
        NettyHttpServe server = new NettyHttpServe(port, Arrays.asList(proxyServers.split(",")));
        System.out.println("netty server started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
