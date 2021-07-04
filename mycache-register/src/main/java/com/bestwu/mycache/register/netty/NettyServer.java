package com.bestwu.mycache.register.netty;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 20:06 <br>
 */
public interface NettyServer extends AutoCloseable{
    /**
     * open connection
     */
    void openConnection();


}
