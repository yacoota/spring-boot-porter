package com.cacoota.porter;

import com.cacoota.framework.common.C;
import com.cacoota.framework.common.IS;
import com.cacoota.framework.spring.SpringApplicationContext;
import com.cacoota.porter.config.TaskConfig;
import com.cacoota.porter.config.PorterConfig;
import com.cacoota.porter.plugin.observe.QueueObservable;
import com.cacoota.porter.task.ITask;
import com.cacoota.porter.task.Process;
import com.cacoota.porter.task.ProcessUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@Configuration
@Slf4j
public class ProductTask implements ITask {


    @Autowired
    private PorterConfig config;

    @Autowired
    private Process process;

    @Autowired
    private QueueObservable observable;

    // 保存上一次检索的文件列表
    @Getter
    @Setter
    private ConcurrentHashMap<String, HashSet<String>> previous = new ConcurrentHashMap<String, HashSet<String>>();

    // 采用方法获取，不自动注入了。
    public ConcurrentHashMap<String, LinkedBlockingQueue<String>> getCurrentQueue() {
        QueueObservable product = (QueueObservable)  SpringApplicationContext.getBean("observable");
        return product.getShare().getQueues();
    }

    // 采用方法获取，不自动注入了。topic-->文件队列
    public void setCurrentQueue(ConcurrentHashMap<String, HashSet<String>> exceed) {
        QueueObservable product = (QueueObservable)  SpringApplicationContext.getBean("observable");
        ConcurrentHashMap<String, LinkedBlockingQueue<String>> queue = product.getShare().getQueues();
        for (Map.Entry<String, HashSet<String>> entry : exceed.entrySet()) {
            if (queue.containsKey(entry.getKey())) {
                if (queue.get(entry.getKey()).isEmpty()) {
                    queue.put(entry.getKey(), conversion(entry.getValue(), new LinkedBlockingQueue<>()));
                } else {
                    queue.put(entry.getKey(), conversion(entry.getValue(), queue.get(entry.getKey())));
                }
            } else {
                queue.put(entry.getKey(), conversion(entry.getValue(), new LinkedBlockingQueue<>()));
            }
        }
    }

    private LinkedBlockingQueue<String> conversion(HashSet<String> set, LinkedBlockingQueue<String> link) {
        String[] temp = set.toArray(new String[]{});
        for (int i = 0; i < temp.length; i++) {
            link.offer(temp[i]);
        }
        return link;
    }

    // 获取需要添加如队列的数据：只增加本次额外增加的文件名列表。
    public ConcurrentHashMap<String, HashSet<String>> parse(ConcurrentHashMap<String, HashSet<String>> pre, ConcurrentHashMap<String, HashSet<String>> cur) {
        ConcurrentHashMap<String, HashSet<String>> map = new ConcurrentHashMap<>();
        for (Map.Entry<String, HashSet<String>> entry : cur.entrySet()) {
            if (pre.containsKey(entry.getKey())) {
                HashSet<String> preSet = pre.get(entry.getKey());
                for (String path : entry.getValue()) {
                    if (!preSet.contains(path) || StringUtils.endsWithIgnoreCase(path, C.RUN)) {
                        if (map.containsKey(entry.getKey())) {
                            map.get(entry.getKey()).add(path);
                        } else {
                            HashSet<String> newSet = new HashSet<>();
                            newSet.add(path);
                            map.put(entry.getKey(), newSet);
                        }
                    }
                }
            } else {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    //扫描当前目录下所有文件
    public void recordFiles(File file, HashSet<String> pathSet) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] fileArray = file.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        if(!fileArray[i].isDirectory()){
                            pathSet.add(fileArray[i].getPath());
                        }
                    }
                }
            } else {
                pathSet.add(file.getPath());
            }
        }
    }

    // 获取需要添加如队列的数据
    public void record(TaskConfig task, ConcurrentHashMap<String, HashSet<String>> data) {
        String uuid = task.getUuid();
        String topic = task.getTopic();
        HashSet<String> pathSet = new HashSet<>();
        List<String> paths = task.getPaths();
        for (String path : paths) {
            recordFiles(new File(path), pathSet);
        }
        data.put(topic+"&"+uuid, pathSet);
    }

    // 定期扫描配置中的路径：以topic为key遍历
    @Schedules(value = {@Scheduled(fixedRateString = "${task.business.product.work.fix_rate}", initialDelayString = "${task.business.product.work.initial_delay}")})
    public void execute() {
        if (!IS.product()) return; // 拦截，查看参数配置信息
        System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！生产者项目启动！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        List<TaskConfig> tasks = config.product();
        // Assert.notEmpty(tasks, "系统初始化配置加载失败，请联系管理员检查!");
        ConcurrentHashMap<String, HashSet<String>> current = new ConcurrentHashMap<>();
        for (TaskConfig task : tasks) {
            String group = task.getGroup();
            Assert.hasText(group, "主键信息获取失败----->执行主机无法分配!");
            log.debug("当前监测任务: {}", task.toString());
            if (ProcessUtil.isExecute(group, process.getProcess(), process.getCurrentProcess())) {
                log.info("当前监测任务[正常运行]: {}", task.toString());
                // 记录当前的扫描结果
                record(task, current);
            } else {
                log.info("当前监测任务[阻止运行]: {}", task.toString());
                continue;
            }
        }
        // 比较前一次的结果和当前的差异，并加入到现有的队列中。
        setCurrentQueue(parse(previous, current));
        // 记录前一个信息。
        previous = current;
        //调用观察模型处理后续流程！
        observable.send();
    }

}