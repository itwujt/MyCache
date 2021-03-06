package com.bestwu.mycache.register.netty;

import com.bestwu.mycache.register.Receiver;
import com.bestwu.mycache.register.netty.handler.EchoMyCacheServerHandler;
import com.bestwu.mycache.register.netty.handler.HeartBeatHandler;
import com.bestwu.mycache.register.netty.handler.RegisterConnectionRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 21:15 <br>
 */
@Slf4j
public class TcpReceiver implements NettyServer, Receiver {

    private ServerBootstrap serverBootstrap;

    private EventLoopGroup boss;

    private EventLoopGroup worker;

    private ChannelFuture channelFuture;

    public TcpReceiver() {
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
        // 处理大数据流
        pipeline.addLast("streamer", new ChunkedWriteHandler());
        // 处理连接
        pipeline.addLast("registerConnectionRequestHandler", new RegisterConnectionRequestHandler());
        pipeline.addLast("echoMyCacheServerHandler", new EchoMyCacheServerHandler());
        // 处理心跳
        pipeline.addLast("idleStateHandler", new IdleStateHandler(20L, 40L, 60L, TimeUnit.SECONDS));
        pipeline.addLast("heartBeatHandler", new HeartBeatHandler());

    }

    @Override
    public void receive() {
        try {
            this.channelFuture = this.serverBootstrap.bind("127.0.0.1", 9999).sync();
            log.info("TCP Register open connection!");
            this.channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != this.boss) {
                this.boss.shutdownGracefully();
            }
            if (null != this.worker) {
                this.worker.shutdownGracefully();
            }
        }
    }

    /**
     * open connection
     */
    @Override
    public void openConnection() {
        receive();
    }


    @Override
    public void close() throws Exception {

    }
}
