package com.bestwu.mycache.register.netty.handler;

import com.bestwu.mycache.register.netty.ChannelAttributes;
import com.bestwu.mycache.register.repository.MyCacheServerRepo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO <br>
 *
 * @author Best Wu
 * @date 2021/7/7 22:59 <br>
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        final Channel channel = ctx.channel();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            IdleState state = event.state();
            switch (state) {
                case READER_IDLE:
                    log.warn("进入读空闲... " + System.currentTimeMillis());
                    // 如果读空闲说明 MyCache Server 未向 Register 发送心跳
                    String registerKey = channel.attr(ChannelAttributes.ATTR_REGISTER_KEY).get();
                    MyCacheServerRepo.offline(registerKey);
                    log.warn("Channel:{} 读空闲，Unique Key:{}，已移除仓库中该通道，即将关闭通道...", channel, registerKey);
                    channel.close().addListener(future -> {
                       if (future.isSuccess()) {
                           log.info("通道 Channel:{} 由于读空闲...已断开连接", channel);
                       }
                    });
                    break;
                case WRITER_IDLE:
                    log.warn("进入写空闲... " + System.currentTimeMillis());
                    break;
                case ALL_IDLE:
                    log.warn("进入读写空闲... " + System.currentTimeMillis());
                    break;
                default: break;
            }
        }
    }
}
