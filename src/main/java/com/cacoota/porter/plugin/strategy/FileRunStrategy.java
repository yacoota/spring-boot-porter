package com.cacoota.porter.plugin.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("runFile")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class FileRunStrategy extends AbstractStrategy {

    public void before() throws IOException {
        log.debug("断点文件处理开始: {}", "文件[" + name + "]处理：" + System.currentTimeMillis());
    }

    @Override
    public void process() {
        log.debug("处理断点文件: {}", name);

    }

    public FileType type() {
        return FileType.RUN;
    }
}
