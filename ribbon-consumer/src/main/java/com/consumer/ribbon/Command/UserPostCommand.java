package com.consumer.ribbon.Command;

import com.consumer.ribbon.bean.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

public class UserPostCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private User user;

    public UserPostCommand(RestTemplate restTemplate,User user){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandKey")));
        this.restTemplate = restTemplate;
        this.user = user;
    }


    @Override
    protected User run() throws Exception {
        //写操作
        User user1 = restTemplate.postForObject("http://localhost:8082/users", user, User.class);
        //刷新缓存 清理缓存中失败的user
        UserGetCommand.flushCache(user.getId());
        return user1;
    }
}
