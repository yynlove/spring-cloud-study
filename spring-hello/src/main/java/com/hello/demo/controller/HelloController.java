package com.hello.demo.controller;


import com.hello.demo.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


@RestController
public class HelloController {

    private static final  Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * 主要使用get方法进行请求
     */

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello")
    public String index() throws InterruptedException {
        List<String> services = client.getServices();
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:"+sleepTime);
        Thread.sleep(sleepTime);
        for (String s:services){
            logger.info(s);
        }
        List<ServiceInstance> instances = client.getInstances("hello-service");
        for (ServiceInstance instance:instances){
            logger.info("spring-hello ,host:"+instance.getHost()+"serviceId:"+instance.getServiceId());
        }

        return  "Hello world";
    }

        @RequestMapping(value = "/user",method = RequestMethod.GET)
        public String user(@RequestParam String user){
            if (!user.equals("yuan")){
                user="yenan";
            }
            return user;
        }

        @RequestMapping(value = "/name",method = RequestMethod.GET)
        public String name(@RequestParam String name){
            return  name;
        }

        @RequestMapping(value = "/getUser",method = RequestMethod.GET)
        public User getUser(){
            User user = new User();
            user.setId("123456");
            user.setName("yuanyenan");
            return user;
        }

        @RequestMapping(value = "/insertOne",method = RequestMethod.POST)
        public User insertOne(@RequestBody User user){
            user.setId("12345");
            return user;
        }

        @RequestMapping(value = "/updateOne/{id}",method = RequestMethod.PUT)
        public void updateOne(@RequestBody User user, @PathVariable String id){
            user.setId(id);
            System.out.println(user.toString());
        }

        @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
        public void deleteOne(@PathVariable int id){
            System.out.println("删除ID："+id+"数据。");
        }
}
