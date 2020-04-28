package com.cacoota.porter.task;

import com.cacoota.framework.common.IC;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class Process implements IProcess {

    @Getter
    private int process;

    @Getter
    private String currentProcess = IC.GLOBAL_EXECUTE_CURRENT_PROCESS;


}
