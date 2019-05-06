package com.consumer.ribbon.Command;

import com.consumer.ribbon.bean.User;
import com.consumer.ribbon.service.UserService;
import com.netflix.hystrix.HystrixCommand;

import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

/**
 * 准备一个批量请求命令的实现 run()方法 返回一个List<user>
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {

    UserService userService;
    List<Long> userIds;

    public UserBatchCommand( UserService userService, List<Long> userIds) {
        super(Setter.withGroupKey(asKey("userServiceCommand")));
        this.userService = userService;
        this.userIds = userIds;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.findAll(userIds);
    }
}
