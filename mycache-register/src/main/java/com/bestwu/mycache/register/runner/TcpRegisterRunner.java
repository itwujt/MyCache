package com.bestwu.mycache.register.runner;

import com.bestwu.mycache.register.netty.TcpRegister;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/6 23:20 <br>
 */
@Component
public class TcpRegisterRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> new TcpRegister().openConnection()).start();
    }
}
