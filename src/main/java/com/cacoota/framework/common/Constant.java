package com.cacoota.framework.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("constant")
@Configuration
public class Constant {

    @Value("${file-suffix-log}")
    @Getter
    private String log;

    @Value("${file-suffix-dat}")
    @Getter
    private String dat;

    @Value("${file-suffix-run}")
    @Getter
    private String run;

    @Value("${file-suffix-exit}")
    @Getter
    private String exit;

    @Value("${file-new-directory}")
    @Getter
    private String directory;

    @Value("${file-suffix}")
    @Getter
    private String suffix;

    @Value("${file-max-line}")
    @Getter
    private int maxLine;

    @Value("${file-max-size}")
    @Getter
    private int maxSize;

    @Value("${file-max-time}")
    @Getter
    private int maxTime;

}
