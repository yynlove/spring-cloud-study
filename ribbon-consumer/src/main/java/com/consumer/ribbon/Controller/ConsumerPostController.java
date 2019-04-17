package com.consumer.ribbon.Controller;

import com.consumer.ribbon.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerPostController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/postOne")
    public String insertOne(){
        User user = new User("yuanyenan", "123456");
        return restTemplate.postForEntity("http://HELLO-SERVICE/insertOne",user,String.class).getBody();

    }



}
