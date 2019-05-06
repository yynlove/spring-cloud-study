package com.feign.demo.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "HELLO-SERVICE")
public interface RefactorHelloService  extends com.space.service.HelloService {
}
