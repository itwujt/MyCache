package com.bestwu.mycache.server.netty.handler;

import com.bestwu.mycache.common.dto.RegisterPacketDTO;
import com.bestwu.mycache.tool.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/13 23:24 <br>
 */
@Slf4j
@Sharable
public class TcpRegisterHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // TODO 通道注册 向 Register Server 发送 注册信息
        final Channel channel = ctx.channel();
        log.info("通道 Channel: {} 已注册，即将发送注册信息至 MyCache Register，通道还未处于激活状态!", channel);
        RegisterPacketDTO registerPacketDTO = buildRegisterPacket();
        log.info("通道 Channel: {} 构建 RegisterPacket 成功，如示: {}", channel, registerPacketDTO);
        String dto = JsonUtil.toJsonString(registerPacketDTO);
        channel.writeAndFlush(Unpooled.wrappedBuffer(dto.getBytes(StandardCharsets.UTF_8)));
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }


    /**
     * TODO 思考这块可以从配置文件中拿，不想从 Spring 的方式获取
     * build RegisterPacketDTO
     * @return com.bestwu.mycache.common.dto.RegisterPacketDTO
     */
    private RegisterPacketDTO buildRegisterPacket() {
        String dataGroup = "";
        String ip = "";
        String wsPort = "";
        String vRandom = "";
        return  RegisterPacketDTO.builder()
                .dataGroup(dataGroup).ip(ip).wsPort(wsPort).vRandom(vRandom).build();
    }
}
