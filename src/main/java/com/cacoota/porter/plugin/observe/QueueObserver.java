package com.cacoota.porter.plugin.observe;

import com.alibaba.fastjson.JSON;
import com.cacoota.framework.common.C;
import com.cacoota.framework.spring.SpringApplicationContext;
import com.cacoota.porter.plugin.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Component("observer")
@Configuration
@Slf4j
public class QueueObserver implements Observer, IObserve {

    // 处理DAT文件线程池
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    @Override
    public void update(Observable o, Object arg) {
        ConcurrentHashMap<String, LinkedBlockingQueue<String>> queues = ((QueueObservable) o).getShare().getQueues();
        log.debug("队列接收信息: {}", JSON.toJSONString(queues));
        // 解析队列信息，进行任务的策略分发
        LinkedBlockingQueue<String> queue = null;
        for (Map.Entry<String, LinkedBlockingQueue<String>> entry : queues.entrySet()) {
            queue = entry.getValue();
            while (!queue.isEmpty()) {
                work(queue.poll(), entry.getKey());
            }
        }
        log.debug("队列保留元素: {}", JSON.toJSONString(queues));
    }

    public IStrategy parse(String file) {
        return StringUtils.endsWith(file, C.LOG) ?
                (FileLogStrategy) SpringApplicationContext.getBean("logFile") :
                StringUtils.endsWith(file, C.EXIT) ?
                        (FileExitStrategy) SpringApplicationContext.getBean("exitFile") :
                        StringUtils.endsWith(file, C.RUN) ?
                                (FileRunStrategy) SpringApplicationContext.getBean("runFile") :
                                (FileDatStrategy) SpringApplicationContext.getBean("datFile");
    }

    // 线程引入执行，加快效率
    public void work(String file, String topic) {
        IStrategy strategy = parse(file);
        if (strategy instanceof FileDatStrategy) {
            executor.submit(new FileCallable(strategy, file, topic));
        } else {
            new Thread(new FileRunable(strategy, file, topic)).start();
        }
    }
}

