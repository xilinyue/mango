package com.jun.my_mango.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年11月11日19:28
 */
@EnableAdminServer
@SpringBootApplication
public class MyMangoMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyMangoMonitorApplication.class, args);
    }
}
