package com.cacoota.porter.plugin.strategy;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
public abstract class AbstractStrategy implements IStrategy {

    @Getter
    @Setter
    protected String name;

    @Getter
    @Setter
    protected String uuid;


    public void before() throws IOException {
        log.debug("文件处理开始: {}", "文件[" + name + "]处理：" + System.currentTimeMillis());
    }

    public void after() throws IOException {
        log.debug("文件处理完成: {}", "文件[" + name + "]处理：" + System.currentTimeMillis());
    }


    // 直接对外开放接口，对过程进行封装！
    @Override
    public void execute(String file, String topic) {
        try {
            before();
            process();
            after();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public abstract void process() throws Exception;

}
