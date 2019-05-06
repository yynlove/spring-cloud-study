package com.hello.demo.controller;

import com.hello.demo.bean.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloFeignController {

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return "Hello " +name;
    }

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public User hello(@RequestHeader String name,@RequestHeader String id){
        return new User(name,id);
    }


    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        return "hello " + user.getName()+" , " + user.getId();
    }
}
