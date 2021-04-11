package com.stream.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(value = {SinkSender.class})
public class SinkReceiver {

   private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    /**
     * 消息通道的消费者
     * @param payload
     */
   @StreamListener(Sink.INPUT)
   public void receive(Object payload){
       logger.info("received: " + payload);
   }

}
