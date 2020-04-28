package com.cacoota.porter.plugin.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("datFile")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class FileDatStrategy extends AbstractStrategy {

    @Override
    public void process() throws Exception {

    }

    public FileType type() {
        return FileType.DAT;
    }

}
