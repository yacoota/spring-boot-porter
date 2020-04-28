package com.cacoota.porter.plugin.share;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component("share")
public class ShareSecurityQueue {

    // 共享区队列数据：以topic为主键，每个主键存放阻塞队列处理。
    @Setter
    @Getter
    private ConcurrentHashMap<String, LinkedBlockingQueue<String>> queues = new ConcurrentHashMap<String, LinkedBlockingQueue<String>>();

}
