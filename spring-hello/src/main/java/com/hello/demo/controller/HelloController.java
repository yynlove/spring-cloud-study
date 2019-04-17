package com.hello.demo.controller;


import com.hello.demo.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    private static final  Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * 主要使用get方法进行请求
     */

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello")
    public String index(){
       /* List<String> services = client.getServices();
        for (String s:services){
            logger.info(s);
        }
        List<ServiceInstance> instances = client.getInstances("hello-service");
        for (ServiceInstance instance:instances){
            logger.info("spring-hello ,host:"+instance.getHost()+"serviceId:"+instance.getServiceId());
        }*/
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
}
