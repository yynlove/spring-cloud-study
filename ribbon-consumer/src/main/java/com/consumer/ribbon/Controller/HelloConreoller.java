package com.consumer.ribbon.Controller;

import com.consumer.ribbon.Command.UserCommand;
import com.consumer.ribbon.bean.User;
import com.consumer.ribbon.service.HelloService;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HelloConreoller {

    @Autowired
    HelloService helloService;



    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService.helloService();
    }

    //同步方式 - 注解
    @RequestMapping(value = "/zhujie-user")
    public User getUser(){
      return  helloService.zhuJieUser(Long.valueOf(123));
    }
    //异步方式 - 注解
    @RequestMapping(value = "/zhujieAsyc-user")
    public User getUserByAsyc(){
        Future<User> userByAsyc = helloService.getUserByAsyc(Long.valueOf(123));
        User user = null;
        try {
            user = userByAsyc.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }

    //同步-继承方式
    @RequestMapping(value = "/extends-user")
    public User extendsUser(){
       User user = new UserCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000))
                ,new RestTemplate(), 1L).execute();
       return user;
    }


    //异步-继承方式
    @RequestMapping(value = "/extendsAsyc-user")
    public User extendsUserByAsyc() throws ExecutionException, InterruptedException {
        Future<User> queue = new UserCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000))
                , new RestTemplate(), 1L).queue();
        User user = queue.get();
        return user;
    }

}
