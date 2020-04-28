package com.cacoota.porter.plugin.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("logFile")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class FileLogStrategy extends AbstractStrategy {
    @Override
    public void process() {
        log.debug("处理日志文件", name);
    }


    public FileType type() {
        return FileType.LOG;
    }

}
