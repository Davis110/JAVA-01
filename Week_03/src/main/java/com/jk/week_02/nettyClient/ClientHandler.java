package com.jk.week_02.nettyClient;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author wei.huang
 * @version Id: ClientHandler.java, v 0.1 2021年01月31日  10:22 wei.huang Exp $
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private String result;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            // 读取请求结果
            ByteBuf bb = (ByteBuf) msg;
            byte[] respByte = new byte[bb.readableBytes()];
            bb.readBytes(respByte);
            String respStr = new String(respByte, "UTF-8");
            System.err.println("client--收到响应：" + respStr);
            result = respStr;
            // 请求解结果处理
            handlerObject(ctx, msg);
        } finally {
            // 必须释放msg数据
            ReferenceCountUtil.release(msg);
        }
    }

    public String getResult() {
        return result;
    }

    private void handlerObject(ChannelHandlerContext ctx, Object msg) {
        System.err.println("server 获取信息：" + JSON.toJSONString(msg));
    }

    // 数据读取完毕的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("客户端读取数据完毕");
    }

    // 出现异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client 读取数据出现异常");
        ctx.close();
    }

}
