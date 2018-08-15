package com.anjiplus.sell.contorller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: Kean
 * @Date: 2018/8/15 下午10:27
 * @Description:
 */
@RestController
@RequestMapping("weixin")
@Slf4j
public class WeixinController {
    @GetMapping("/auth")
    public void auto(@RequestParam("code") String code){
        log.info("访问到达");
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd898fcb01713c658&secret=29d8a650db31472aa87800e3b0d739f2&code=" + code + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
///sell/wechat/authorize

    }

}
