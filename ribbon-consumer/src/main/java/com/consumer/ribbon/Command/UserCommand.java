package com.consumer.ribbon.Command;

import com.consumer.ribbon.bean.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

/**
 * 继承方式
 */
public class UserCommand extends HystrixCommand<User> {


    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(Setter setter,RestTemplate restTemplate,Long id){
        super(setter);
        this.restTemplate = restTemplate;
        this.id= id;
    }

    @Override
    protected User run() throws Exception {
        //这个地方目前不能用HELLO-SERVICE （未知原因）
        return restTemplate.getForObject("http://localhost:8081/user/{1}",User.class,id);
    }
    //服务降级
    @Override
    protected User getFallback(){
        return new User();
    }





}
