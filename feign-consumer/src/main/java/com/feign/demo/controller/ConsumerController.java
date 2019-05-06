package com.feign.demo.controller;

import com.feign.demo.bean.User;
import com.feign.demo.service.HelloService;
import com.feign.demo.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @Autowired
    RefactorHelloService refactorHelloService;

    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService.hello();
    }


    @RequestMapping(value = "feign-consumer2",method = RequestMethod.GET)
    public String helloConsumer2(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(helloService.hello()).append("\n");
        stringBuilder.append(helloService.hello("yuan")).append("\n");
        stringBuilder.append(helloService.hello("yenan","123456")).append("\n");
        stringBuilder.append(helloService.hello(new User("yuanyenan","123123"))).append("\n");
        return stringBuilder.toString();

    }

    @RequestMapping(value = "feign-consumer3",method = RequestMethod.GET)
    public String helloConsumer3(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(refactorHelloService.hello("yuan1")).append("\n");
        stringBuilder.append(refactorHelloService.hello("yenan1","123456789")).append("\n");
        stringBuilder.append(refactorHelloService.hello(new com.space.dto.User("yuan1yenan","123123123"))).append("\n");
        return stringBuilder.toString();

    }

}
