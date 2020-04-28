package com.cacoota.framework.spring;

import com.cacoota.framework.common.IC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class ApplicationContextService implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        log.info(context.toString());
        // 手工加载初始化相关数据信息
        IC.load();
    }

}
