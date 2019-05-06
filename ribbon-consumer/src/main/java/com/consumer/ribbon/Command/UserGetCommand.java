package com.consumer.ribbon.Command;

import com.consumer.ribbon.bean.User;
import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

public class UserGetCommand  extends HystrixCommand<User>{

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");

    private RestTemplate restTemplate;
    private Long id;

    public UserGetCommand(RestTemplate restTemplate, Long id){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandKey")));
        this.restTemplate = restTemplate;
        this.id= id;
    }

    @Override
    protected User run() throws Exception {
        //这个地方目前不能用HELLO-SERVICE （未知原因）
        return restTemplate.getForObject("http://localhost:8082/user/{1}",User.class,id);
    }
    //服务降级
    @Override
    protected User getFallback(){
        return new User();
    }

    //请求缓存
    @Override
    protected String getCacheKey(){
        //根据id置入缓存
        System.out.println("存入缓存");
        return String.valueOf(id);
    }

    public static void flushCache(String id){
        //刷新缓存，根据id进行清理
        System.out.println("缓存清理");
        HystrixRequestCache.getInstance(GETTER_KEY,
                HystrixConcurrencyStrategyDefault.getInstance()).clear(id);
    }

}
