package com.cacoota.framework.common;

import com.cacoota.framework.spring.SpringApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class C {
    public static final String LOG;
    public static final String DAT;
    public static final String RUN;
    public static final String EXIT;
    public static final String DIR;
    public static final String FILE_SUFFIX;
    public static final int FILE_MAX_LINE;
    public static final int FILE_MAX_SIZE;
    public static final int FILE_MAX_TIME;

    public static final String PRODUCT_TYPE = "1";
    public static final String CUSTOMER_TYPE = "2";

    public static final int CUSTOMER_DEFAULT_THREAD_NUMS = 10;


    static {
        Constant c = init();
        LOG = c.getLog();
        DAT = c.getDat();
        RUN = c.getRun();
        EXIT = c.getExit();
        DIR = c.getDirectory();
        FILE_SUFFIX = c.getSuffix();
        FILE_MAX_LINE = c.getMaxLine();
        FILE_MAX_SIZE = c.getMaxSize();
        FILE_MAX_TIME = c.getMaxTime();
    }

    private static final synchronized Constant init() {
        return (Constant) SpringApplicationContext.getBean("constant");
    }

    public static final synchronized Constant init(ApplicationEvent event) {
        return (Constant) ((ContextRefreshedEvent) event).getApplicationContext().getBean("constant");
    }

    // 做存根，加载初始化此类即可初始化完成所有参数！空转
    public static void load() {

    }

}
