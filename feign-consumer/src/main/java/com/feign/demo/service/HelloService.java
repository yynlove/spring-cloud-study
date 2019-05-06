package com.feign.demo.service;

import com.feign.demo.bean.User;
import com.feign.demo.service.impl.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "hello-service",fallback = HelloServiceFallback.class) //通过注解绑定服务名
public interface HelloService {

    @RequestMapping("/hello") //该服务提供的REST接口
    String hello();

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name,@RequestHeader("id") String id);

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody User user);


}
