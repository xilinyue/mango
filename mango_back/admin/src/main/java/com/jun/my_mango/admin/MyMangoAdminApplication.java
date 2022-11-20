package com.jun.my_mango.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.jun.my_mango"})
//@EnableSwagger2
public class MyMangoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMangoAdminApplication.class, args);
    }

}
