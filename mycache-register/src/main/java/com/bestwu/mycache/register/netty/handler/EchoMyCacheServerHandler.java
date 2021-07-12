package com.bestwu.mycache.register.netty.handler;

import com.bestwu.mycache.common.dto.RegisterPacketDTO;
import com.bestwu.mycache.common.dto.RegisterResponseDTO;
import com.bestwu.mycache.register.netty.ChannelAttributes;
import com.bestwu.mycache.register.repository.MyCacheServerRepo;
import com.bestwu.mycache.tool.JsonUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

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
        String dataGroup = registerPacketDTO.getDataGroup();
        String ip = registerPacketDTO.getIp();
        String wsPort = registerPacketDTO.getWsPort();
        String vRandom = registerPacketDTO.getVRandom();
        // TODO ?这块先设置四个参数用井号连接，后续可能会有改动
        String key = String.join("#", dataGroup, ip, wsPort, vRandom);
        // 向通道中注入属性
        final Channel channel = ctx.channel();
        channel.attr(ChannelAttributes.ATTR_DATA_GROUP).set(dataGroup);
        channel.attr(ChannelAttributes.ATTR_IP).set(ip);
        channel.attr(ChannelAttributes.ATTR_WS_PORT).set(wsPort);
        channel.attr(ChannelAttributes.ATTR_V_RANDOM).set(vRandom);
        channel.attr(ChannelAttributes.ATTR_REGISTER_KEY).set(key);
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

    }
}
