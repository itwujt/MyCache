package com.bestwu.mycache.register.repository;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 13:34 <br>
 */
public class MyCacheServerRepo {
    /**
     * key: dataGroup#ip#wsport#v_random
     * value: server channel
     */
    public static Map<String, Channel> myCacheServerRepo = new ConcurrentHashMap<>(16);

    public static Channel register(String key, Channel serverChannel) {
        return myCacheServerRepo.put(key, serverChannel);
    }

    public static Channel offline(String key) {
        return myCacheServerRepo.remove(key);
    }

    public static boolean keepLine(String key) {
        return myCacheServerRepo.containsKey(key);
    }
}
