package com.consumer.ribbon.service.impl;

import com.consumer.ribbon.bean.User;
import com.consumer.ribbon.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public User fing(Long id) {
        return restTemplate.getForObject("http://HELLO-SERVICE/users/{1}",User.class,id);
    }

    @Override
    public List<User> findAll(List<Long> ids) {
        return (List<User>) restTemplate.getForObject("http://HELLO-SERVICE/users?ids={1}",User.class, StringUtils.join(ids,","));
    }
}
