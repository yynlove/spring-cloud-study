package com.consumer.ribbon.service;

import com.consumer.ribbon.bean.User;

import java.util.List;

public interface UserService {

    public User fing(Long id);

    public List<User> findAll(List<Long> ids);

}
