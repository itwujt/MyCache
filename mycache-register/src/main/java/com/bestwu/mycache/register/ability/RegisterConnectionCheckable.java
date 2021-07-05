package com.bestwu.mycache.register.ability;

import com.bestwu.mycache.common.dto.RegisterPacketDTO;
import io.netty.channel.ChannelHandlerContext;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/5 23:02 <br>
 */
public interface RegisterConnectionCheckable<T extends Object> {

    default RegisterPacketDTO registerConnectionCheck(ChannelHandlerContext ctx, T msg) throws Exception{
        return null;
    }
}
