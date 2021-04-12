package com.stream.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBinding(value = {Processor.class})
public class App1 {

   private static Logger logger =  LoggerFactory.getLogger(App1.class);

   //@StreamListener(Processor.INPUT)
   //@SendTo(Processor.OUTPUT)
   @ServiceActivator(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
   public Object receiveFromInput(Object payLoad){
       logger.info("APP1接收到消息-"+ payLoad);
       return "返回-"+payLoad;
   }

}
