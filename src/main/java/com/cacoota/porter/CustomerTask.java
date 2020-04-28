package com.cacoota.porter;

import com.cacoota.framework.common.IS;
import com.cacoota.porter.config.TaskConfig;
import com.cacoota.porter.config.PorterConfig;
import com.cacoota.porter.task.ITask;
import com.cacoota.porter.task.Process;
import com.cacoota.porter.task.ProcessUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Configuration
@Slf4j
public class CustomerTask implements ITask {


    @Autowired
    private PorterConfig config;

    @Autowired
    private Process process;

    // 保存所有的检索的话题列表
    @Getter
    @Setter
    private Set<String> history = Collections.synchronizedSet(new HashSet<String>());

    // 记录当前服务器需要执行的话题
    public void record(String topic, Set<String> data) {
        data.add(topic);
    }

    // 当前需要发送到后台去执行的话题，已发送到不能再次（只有动态可以加载配置文件的时候才起效，否则始终不会变的）
    public void setDisposeSet(Set<String> current) {
        Set<String> datas = Collections.synchronizedSet(current);
        String[] data = datas.toArray(new String[]{});
        for (String temp : data) {
            if (history.contains(temp)) {
                current.remove(temp);
            }
        }
    }

    public TaskConfig getConfig(String uuid, List<TaskConfig> tasks) {
        for (TaskConfig config : tasks) {
            if (StringUtils.equals(uuid, config.getUuid())) {
                return config;
            }
        }
        return null;
    }

    // 定期扫描配置中的路径：以topic为key遍历
    @Schedules(value = {@Scheduled(fixedRateString = "${task.business.customer.work.fix_rate}", initialDelayString = "${task.business.customer.work.initial_delay}")})
    public void execute() {
        if (!IS.customer()) return; // 拦截，查看参数配置信息
        System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！消费者项目启动！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        List<TaskConfig> tasks = config.customer();
        // Assert.notEmpty(tasks, "系统初始化配置加载失败，请联系管理员检查!");
        Set<String> current = Collections.synchronizedSet(new HashSet<>());
        for (TaskConfig task : tasks) {
            String group = task.getGroup();
            if (ProcessUtil.isExecute(group, process.getProcess(), process.getCurrentProcess())) {
                log.info("当前监测任务[正常运行]: {}", task.toString());
                record(task.getUuid(), current);
            } else {
                log.info("当前监测任务[阻止运行]: {}", task.toString());
                continue;
            }
        }
        setDisposeSet(current);
        log.debug("当前存档话题数：" + history.size() + ";当前新增话题数：" + current.size());
    }


}