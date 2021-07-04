package com.bestwu.mycache.register.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 21:34 <br>
 */
public class RegisterConnectionHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // TODO？检查是否为连接请求
    }
}
