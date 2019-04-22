package com.consumer.ribbon.Command;

import com.consumer.ribbon.bean.User;
import com.netflix.hystrix.HystrixCommand;
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
        return restTemplate.getForObject("http://HELLO-SERVICE/user/{1}",User.class,id);
    }
    //服务降级
    @Override
    protected User getFallback(){


        return new User();
    }


}
