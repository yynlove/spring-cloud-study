package com.hello.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private static final  Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){
        List<String> services = client.getServices();
        for (String s:services){
            logger.info(s);
        }
        List<ServiceInstance> instances = client.getInstances("hello-service");
        for (ServiceInstance instance:instances){
            logger.info("spring-hello ,host:"+instance.getHost()+"serviceId:"+instance.getServiceId());
        }
        return  "Hello world";
    }

}
