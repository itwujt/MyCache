package com.bestwu.mycache.register.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.bestwu.mycache.common.dto.RegisterPacketDTO;
import com.bestwu.mycache.common.dto.RegisterResponseDTO;
import com.bestwu.mycache.register.repository.MyCacheServerRepo;
import com.bestwu.mycache.tool.JsonUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.StringJoiner;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/7 22:15 <br>
 */
@Sharable
@Slf4j
public class EchoMyCacheServerHandler extends SimpleChannelInboundHandler<RegisterPacketDTO> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterPacketDTO registerPacketDTO) throws Exception {
        Boolean ping = registerPacketDTO.getPing();
        if (Boolean.FALSE.equals(ping)) {
            // 如果是第一次连接
            String dataGroup = registerPacketDTO.getDataGroup();
            String ip = registerPacketDTO.getIp();
            String wsPort = registerPacketDTO.getWsPort();
            String vRandom = registerPacketDTO.getVRandom();
            // TODO ?这块先设置四个参数用井号连接，后续可能会有改动
            String key = String.join("#", dataGroup, ip, wsPort, vRandom);
            final Channel channel = ctx.channel();
            // 通道注册
            MyCacheServerRepo.register(key, channel);
            // echo MyCache server node
            RegisterResponseDTO echoContent = RegisterResponseDTO.builder().code("ACCEPT_CONNECTION").msg("success").build();
            channel.writeAndFlush(JsonUtil.toJsonString(echoContent))
                    .addListener(future -> {
                        if (future.isSuccess()) {
                            log.info("MyCache Server key：{}，通道：{} 连接成功", key, channel);
                        }
                    });
            return;
        }
        // 如果是心跳消息，走心跳处理的 Handler
        ctx.fireChannelRead(registerPacketDTO);
    }
}
