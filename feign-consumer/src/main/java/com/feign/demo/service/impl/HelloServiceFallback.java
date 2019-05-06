package com.feign.demo.service.impl;

import com.feign.demo.bean.User;
import com.feign.demo.service.HelloService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//针对Helloervice 的服务降级
@Component
public class HelloServiceFallback  implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello(@RequestParam("name") String name) {
        return "error";
    }

    @Override
    public User hello(@RequestHeader("name") String name,@RequestHeader("id") String id) {
        return new User("未知","123");
    }

    @Override
    public String hello(@RequestBody User user) {
        return "error";
    }
}
