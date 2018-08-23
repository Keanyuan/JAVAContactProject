package com.anjiplus.order.controller;

import com.anjiplus.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Auther: Kean
 * @Date: 2018/8/23 下午8:51
 * @Description:
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        String message = "now " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }


}
