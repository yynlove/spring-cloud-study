package com.stream.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private App1 app1;

    @Autowired
    private App2 app2;

    @Test
    public void contextLoads() {
       // sinkSender.output().send(MessageBuilder.withPayload("From sinkSender").build());
        app1.receiveFromInput("123456789");
    }

}
