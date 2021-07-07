package com.bestwu.mycache.register.netty.handler;

import com.bestwu.mycache.common.dto.RegisterPacketDTO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * TODO <br>
 *
 * @author Best Wu
 * @date 2021/7/7 22:59 <br>
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }
}
