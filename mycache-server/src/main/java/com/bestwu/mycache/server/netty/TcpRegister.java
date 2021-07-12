package com.bestwu.mycache.server.netty;

import com.bestwu.mycache.server.MyCacheServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;

/**
 * 这个类是处理向 Register Server 注册的 <br>
 *
 * @author Best Wu
 * @date 2021/7/12 23:39 <br>
 */
public class TcpRegister implements NettyClient, MyCacheServer {

    private Bootstrap bootstrap;

    private EventLoopGroup worker;




    /**
     * register to MyCache-Register Server
     *
     * @param host    MyCache-Register server host
     * @param tcpPort MyCache-Register server port
     */
    @Override
    public void registerConnection(String host, String tcpPort) {

    }

    @Override
    public void close() throws Exception {

    }
}
