package com.hello.demo.controller;

import com.space.dto.User;
import com.space.service.HelloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactoeHelloController implements HelloService {
    @Override
    public String hello(String name) {
        return "hello "+ name;
    }

    @Override
    public User hello(String name, String id) {
        return new User(name,id);
    }

    @Override
    public String hello(User user) {
        return "Hello "+ user.getName() +" , "+user.getId();
    }
}
