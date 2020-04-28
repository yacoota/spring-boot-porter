package com.cacoota.porter.plugin.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("exitFile")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class FileExitStrategy extends AbstractStrategy {

    @Override
    public void process() {
        // 移动或者备份完成的文件，扫漏。
        log.debug("处理完工文件: {}", name);
    }

    public FileType type() {
        return FileType.EXIT;
    }

}
