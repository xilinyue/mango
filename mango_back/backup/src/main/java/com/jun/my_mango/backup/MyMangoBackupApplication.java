package com.jun.my_mango.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 启动类
 * @author: Liusu
 * @date: 2022年11月11日16:35
 */
@SpringBootApplication(scanBasePackages = {"com.jun.my_mango"})
public class MyMangoBackupApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyMangoBackupApplication.class, args);
    }
}
