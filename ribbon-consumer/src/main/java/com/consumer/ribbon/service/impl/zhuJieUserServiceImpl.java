package com.consumer.ribbon.service.impl;

import com.consumer.ribbon.bean.User;
import com.consumer.ribbon.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class zhuJieUserServiceImpl implements UserService {

    @Autowired
    RestTemplate restTemplate;



    @HystrixCollapser(batchMethod = "findAll",collapserProperties =
            {@HystrixProperty(name="timerDelayInMilliseconds",value = "100")})
    @Override
    public User fing(Long id) {
        return null;
    }

    @HystrixCommand
    @Override
    public List<User> findAll(List<Long> ids) {
        return restTemplate.getForObject("http://HELLO-SERVICE/users?ids={1}",List.class, StringUtils.join(ids,","));
    }
}
