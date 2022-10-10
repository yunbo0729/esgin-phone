package com.example.demo.task;

import com.example.demo.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTask {
    @Scheduled(cron = "0/10 * * * * ?") //每10秒执行一次
    @Async
    public void scheduledTaskByCorn() {
        log.info("scheduledTaskByCorn定时任务开始 ByCorn：" + DateUtils.timeStamp2Date((System.currentTimeMillis() + "").substring(0, 10),
                "yyyy-MM-dd HH:mm:ss"));
        scheduledTask();
        log.info("scheduledTaskByCorn定时任务结束 ByCorn：" + DateUtils.timeStamp2Date((System.currentTimeMillis() + "").substring(0, 10),
                "yyyy-MM-dd HH:mm:ss"));
    }

    @Scheduled(fixedRate = 10000) //每10秒执行一次
    @Async
    public void scheduledTaskByFixedRate() {
        log.info("scheduledTaskByFixedRate定时任务开始 ByFixedRate：" + DateUtils.timeStamp2Date((System.currentTimeMillis() + "").substring(0, 10),
                "yyyy-MM-dd HH:mm:ss"));
        scheduledTask();
        log.info("scheduledTaskByFixedRate定时任务结束 ByFixedRate：" + DateUtils.timeStamp2Date((System.currentTimeMillis() + "").substring(0, 10),
                "yyyy-MM-dd HH:mm:ss"));
    }

    //@Scheduled(fixedDelay = 10000) //每10秒执行一次
    //@Async
    public void scheduledTaskByFixedDelay() {
        log.info("定时任务开始 ByFixedDelay：" + DateUtils.timeStamp2Date((System.currentTimeMillis() + "").substring(0, 10),
                "yyyy-MM-dd HH:mm:ss"));
        scheduledTask();
        log.info("定时任务结束 ByFixedDelay：" + DateUtils.timeStamp2Date((System.currentTimeMillis() + "").substring(0, 10),
                "yyyy-MM-dd HH:mm:ss"));
    }

    private void scheduledTask() {
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
