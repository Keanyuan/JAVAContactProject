package com.anjiplus.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/*
*
* Task  定时任务
* application 中添加 @EnableScheduling
* 需要开启定时任务的地方添加@Component  该包会被扫描
*
* */
@Component
public class TastTask {
    private static  final SimpleDateFormat dataFormate = new SimpleDateFormat("HH:mm:ss");

//    定义3秒执行任务 表达式生成地址 http://cron.qqe2.com
    @Scheduled(fixedRate = 3000)
    @Scheduled(cron = "2-40 * * * * ? ")
    public void reportCurrentTime() {
        System.out.println("开启定时任务 现在时间： " + dataFormate.format(new Date()));
    }
}
