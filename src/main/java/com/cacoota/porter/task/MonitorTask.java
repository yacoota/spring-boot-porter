package com.cacoota.porter.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class MonitorTask implements ITask {

    // 定期扫描配置中的路径：以topic为key遍历
    @Schedules(value = {@Scheduled(fixedRateString = "${task.business.monitor.work.fix_rate}", initialDelayString = "${task.business.monitor.work.initial_delay}")})
    public void execute() {
       // System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！监听程序启动！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
    }

}