package com.gateway.demo;



import com.gateway.demo.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy //使用注解开启API网关服务功能
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main( String[] args ) {
       new SpringApplicationBuilder(ApiGatewayApplication.class).web(WebApplicationType.SERVLET).run(args);
           // SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }


}
