package com.consumer.ribbon.service;

import com.consumer.ribbon.bean.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;


@Service
public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback1")
    public String helloService(){
        long start = System.currentTimeMillis();
        String body = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
        long end = System.currentTimeMillis();
        logger.info("时间为："+(end-start));
        return body;
    }

    @HystrixCommand(fallbackMethod = "helloFallback")
    public void helloFallback1(){
        //请求另一个网络服务也可能发生失败
    }
    //在fallback()想要获取异常 加一个参数 可以获取异常信息
    public String helloFallback(Throwable e){
        return "error:"+e.getMessage();
    }

    //注解-同步调用 (ignoreExceptions 异常传播 不会触发后续的fallback逻辑)
 //   @HystrixCommand(ignoreExceptions = {HystrixBadRequestException.class})
    //设置命令名称，分组 和线程池
  //  @HystrixCommand(commandKey = "zhuJieUser",groupKey = "userGroup",threadPoolKey = "zhuJieUserThread")
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey") //将结果置入缓存中
    @HystrixCommand
    public User zhuJieUser(Long id) {
        return restTemplate.getForObject("http://HELLO-SERVICE/user/{id}",User.class,id);
    }

    //设置缓存Key
    private String getUserByIdCacheKey(Long id){
        System.out.println("进入设置获取缓存key方法。。。");
        return String.valueOf(id);
    }


    @CacheRemove(commandKey = "zhuJieUser")
    @HystrixCommand
    public User update(@CacheKey("id") User user){
        return restTemplate.postForObject("http://HELLO-SERVICE/users",user,User.class);

    }

    //注解-异步方式
    @HystrixCommand
    public Future<User> getUserByAsyc(Long id) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://HELLO-SERVICE/user/{id}",User.class,id);
            }
        };

    }


}
