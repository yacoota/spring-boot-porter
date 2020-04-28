package com.cacoota.framework.spring;

import com.cacoota.porter.config.base.FileConfig;
import com.cacoota.porter.plugin.observe.QueueObservable;
import com.cacoota.porter.plugin.observe.QueueObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpringApplicationEventListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！刷新完成！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
            getBean("fileconfig", event, FileConfig.class).parse();
            log.info("系统参数初始化，加载参数完成！");
            QueueObservable observable = getBean("observable", event, QueueObservable.class);
            QueueObserver observer = getBean("observer", event, QueueObserver.class);
            observable.addObserver(observer);
            log.info("系统参数初始化，观察模型建立完成！");
        } else if (event instanceof ContextStoppedEvent) {
            System.out.println("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！停止完成！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
            log.info("监听程序", "停止完成");
        }
    }

    protected <T> T getBean(String name, ApplicationEvent event, Class<T> t) {
        return (T) ((ContextRefreshedEvent) event).getApplicationContext().getBean(name);
    }


}
