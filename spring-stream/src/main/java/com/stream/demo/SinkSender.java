package com.stream.demo;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;


public interface SinkSender {
    /**
     * 向消息通道发送数据
     * 通过一个测试类进行消息生产测试
     * @return
     */
    @Output(Sink.INPUT)
    MessageChannel output();

}
