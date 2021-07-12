package com.bestwu.mycache.register.netty;

import io.netty.util.AttributeKey;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/10 15:04 <br>
 */
public final class ChannelAttributes {
    /**
     * 通道中的属性 DataGroup ，Register 接收到注册请求的时候，MyCache Server 携带的参数
     */
    public final static AttributeKey<String> ATTR_DATA_GROUP = AttributeKey.valueOf("dataGroup");
    /**
     * 通道中的属性 ip ，Register 接收到注册请求的时候，MyCache Server 携带的参数
     */
    public final static AttributeKey<String> ATTR_IP = AttributeKey.valueOf("ip");
    /**
     * 通道中的属性 wsPort ，Register 接收到注册请求的时候，MyCache Server 携带的参数
     */
    public final static AttributeKey<String> ATTR_WS_PORT = AttributeKey.valueOf("wsPort");
    /**
     * 通道中的属性 vRandom ，Register 接收到注册请求的时候，MyCache Server 携带的参数
     */
    public final static AttributeKey<String> ATTR_V_RANDOM = AttributeKey.valueOf("vRandom");
    /**
     * 通道中的属性 registerKey ，Register 接收到注册请求的时候，用 MyCache Server 请求参数拼接的数据
     * 拼接规则 dataGroup#ip#wsPort#vRandom
     */
    public final static AttributeKey<String> ATTR_REGISTER_KEY = AttributeKey.valueOf("registerKey");
}
