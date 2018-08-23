package com.anjiplus.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Auther: Kean
 * @Date: 2018/8/23 下午8:47
 * @Description:
 */
public interface StreamClient {


    // BOSS生产者
    String MESSAGE_INPUT = "myMessageInput";
    // ECM消费者
    String MESSAGE_OUTPUT = "myMessageOutput";


    @Input(StreamClient.MESSAGE_INPUT)
    SubscribableChannel input();

    @Output(StreamClient.MESSAGE_OUTPUT)
    MessageChannel output();
}
