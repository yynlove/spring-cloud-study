package com.stream.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.Date;
import java.util.logging.Logger;

@EnableBinding(value = {Processor.class})
public class App2 {

    private static final Logger logger = Logger.getLogger("Process");

    @Bean
    @InboundChannelAdapter(value = Processor.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> timerMessageSource(){
        return ()->new GenericMessage<>(new Date());
    }


    @StreamListener(Processor.INPUT)
    public void receiveFromOut(Object payload){
        logger.info("APP2接收："+payload);
    }
}
