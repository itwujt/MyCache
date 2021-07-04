package com.bestwu.mycache.register.netty;

import com.bestwu.mycache.register.Register;
import com.bestwu.mycache.register.netty.handler.RegisterConnectionHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 21:15 <br>
 */
public class TcpRegister implements NettyServer, Register {

    private ServerBootstrap serverBootstrap;

    private EventLoopGroup boss;

    private EventLoopGroup worker;

    private ChannelFuture channelFuture;

    public TcpRegister() {
        this.serverBootstrap = new ServerBootstrap();
        this.boss = new NioEventLoopGroup();
        this.worker = new NioEventLoopGroup();
        init();
    }

    private void init() {
        this.serverBootstrap.group(this.boss, this.worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        initPipeline(ch.pipeline());
                    }
                });
        initOptions(this.serverBootstrap);
    }

    private void initOptions(ServerBootstrap serverBootstrap) {
        // 使用对象池，重用缓冲区
        serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        // 队列大小
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        // 接收和发送缓冲区 netty 会动态调整
        // 发送缓冲区大小
        // serverBootstrap.childOption(ChannelOption.SO_SNDBUF, 32 * 1024);
        // 接收缓冲区大小
        // serverBootstrap.childOption(ChannelOption.SO_RCVBUF, 32 * 1024);
    }

    private void initPipeline(ChannelPipeline pipeline) {
        pipeline.addLast("streamer", new ChunkedWriteHandler());
        pipeline.addLast("registerConnectionHandler", new RegisterConnectionHandler());
    }

    @Override
    public void receive() {

    }

    /**
     * open connection
     */
    @Override
    public void openConnection() {

    }


    @Override
    public void close() throws Exception {

    }
}
