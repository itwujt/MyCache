package com.bestwu.mycache.server.netty;

import com.bestwu.mycache.server.Register;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 这个类是处理向 Register Server 注册的 <br>
 *
 * @author Best Wu
 * @date 2021/7/12 23:39 <br>
 */
public class TcpRegister implements NettyClient, Register {

    private Bootstrap bootstrap;

    private EventLoopGroup worker;

    public TcpRegister() {
        this.bootstrap = new Bootstrap();
        this.worker = new NioEventLoopGroup();
    }

    public void init() {
        this.bootstrap.group(worker).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        initPipeline(ch.pipeline());
                    }
                });
        initOptions(this.bootstrap);
    }


    private void initPipeline(ChannelPipeline pipeline) {
        pipeline.addLast("streamer", new ChunkedWriteHandler());

    }
    private void initOptions(Bootstrap bootstrap) {
        // 使用对象池，重用缓冲区
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        // 队列大小
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
    }

    /**
     * register to MyCache-Register Server
     */
    @Override
    public void registerConnection() {
        register();
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void register() {

    }
}
