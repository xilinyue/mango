package com.jun.my_mango.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年11月10日11:17
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Object hello(){
        return "hello world";
    }
}
