package com.cacoota.porter.plugin.observe;

import com.alibaba.fastjson.JSON;
import com.cacoota.porter.plugin.share.ShareSecurityQueue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component("observable")
@Slf4j
public class QueueObservable extends Observable implements IObserve {

    @Autowired
    @Getter
    private ShareSecurityQueue share;

    // 监测共享数据区是否存在变动，变动即发送给观察者！
    public void send() {
        ConcurrentHashMap<String, LinkedBlockingQueue<String>> queues = share.getQueues();

        log.debug("当前队列元素: {}", JSON.toJSONString(queues));

        if (!queues.isEmpty()) {
            setChanged();
            notifyObservers();
        }
    }

}
