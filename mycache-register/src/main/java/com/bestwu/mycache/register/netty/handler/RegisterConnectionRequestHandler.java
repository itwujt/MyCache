package com.bestwu.mycache.register.netty.handler;

import com.alibaba.fastjson.JSONException;
import com.bestwu.mycache.common.dto.RegisterPacketDTO;
import com.bestwu.mycache.common.exception.ErrorCodes;
import com.bestwu.mycache.common.exception.RegisterConnectionRefusedException;
import com.bestwu.mycache.common.exception.RegisterPacketNullExeception;
import com.bestwu.mycache.common.exception.RegisterPacketParseFailedException;
import com.bestwu.mycache.register.ability.RegisterConnectionCheckable;
import com.bestwu.mycache.tool.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * <br>
 * @author Best Wu
 * @date 2021/7/4 21:34 <br>
 */
@Slf4j
@Sharable
public class RegisterConnectionRequestHandler extends SimpleChannelInboundHandler<ByteBuf> implements RegisterConnectionCheckable<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ctx.fireChannelRead(registerConnectionCheck(ctx, msg));
    }
    /**
     * <p> 规定发来的消息
     *  json : {
     *      "dataGroup": "xxx",
     *      "ip": "192.168.3.128",
     *      "tcpPort": "9999",
     *      "vRandom": "sdikasjlfkdy"
     *  }
     * @see com.bestwu.mycache.common.dto.RegisterPacketDTO
     * @param ctx ctx
     * @param msg msg
     */
    @Override
    public RegisterPacketDTO registerConnectionCheck(ChannelHandlerContext ctx, ByteBuf msg)
            throws RegisterConnectionRefusedException, IllegalArgumentException,
            RegisterPacketNullExeception, RegisterPacketParseFailedException {
        // 1. 对消息的长度有一个校验，初步定 256 Bytes
        final int limit = 256;
        int len = msg.readableBytes();
        if (len > limit) {
            throw new RegisterConnectionRefusedException(ErrorCodes.REGISTER_REFUSED_ERR_CODE);
        }
        // 2. 校验报文信息是否合法
        byte[] buf = new byte[len];
        String content = new String(buf, StandardCharsets.UTF_8);
        RegisterPacketDTO registerPacketDTO = null;
        try {
            registerPacketDTO = JsonUtil.parseObject(content, RegisterPacketDTO.class);
        } catch (JSONException e) {
            log.error("Register Server 解析注册报文失败！", e);
            throw new RegisterPacketParseFailedException(ErrorCodes.REGISTER_PACKET_PARSE_FAILED_ERR_CODE);
        }
        if (null == registerPacketDTO) {
            throw new RegisterPacketNullExeception(ErrorCodes.REGISTER_PACKET_NULL_ERR_CODE);
        }
        return registerPacketDTO;
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close().addListener(future -> {
            if (future.isSuccess()) {
                log.error("channel:{} 连接已断开", ctx.channel(), cause);
            }
        }).sync();
    }
}
