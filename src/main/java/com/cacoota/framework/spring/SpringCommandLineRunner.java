package com.cacoota.framework.spring;

import com.cacoota.framework.common.IS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpringCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        IS.service(args);
    }
}
