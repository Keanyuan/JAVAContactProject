package com.anjiplus.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Auther: Kean
 * @Date: 2018/8/23 下午8:49
 * @Description:
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(value = StreamClient.MESSAGE_OUTPUT)
//    @SendTo(StreamClient.MESSAGE_OUTPUT)
    public void process(String message){
        log.info("StreamReceiver: " + message);
    }

}
