package com.bestwu.mycache.server;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/12 23:31 <br>
 */
public interface MyCacheServer {
    /**
     * register to MyCache-Register Server
     * @param host MyCache-Register server host
     * @param tcpPort MyCache-Register server port
     */
    default void registerConnection(String host, String tcpPort){

    }

    default void openConnection() {

    }
}
