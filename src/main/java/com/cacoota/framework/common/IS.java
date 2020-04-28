package com.cacoota.framework.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class IS {

    // 生产者服务标记
    private static final String P = "product";
    // 消费者服务标记
    private static final String C = "customer";
    // 默认运行
    private static final String DEFAULT1 = StringUtils.join(new String[]{IS.C, IS.P}, "+");
    private static final String DEFAULT2 = StringUtils.join(new String[]{IS.P, IS.C}, "+");

    private static String SERVICE = null;


    public static final void service(String[] args) {
        if (args.length == 1) {
            String info = parse(args);
            if (!StringUtils.isEmpty(info)) {
                SERVICE = args[0];
                log.info("获取启动参数成功，采用[" + info + "]配置执行！");
            } else {
                // 默认执行消费者和生产者
                SERVICE = DEFAULT1;
                log.info("获取启动参数错误，采用默认配置执行[消费者+生产者]");
            }
        } else {
            // 默认执行消费者和生产者
            SERVICE = DEFAULT2;
            log.info("获取启动参数错误，采用默认配置执行[生产者+消费者]");
        }
    }

    private static final String parse(String[] args) {
        return StringUtils.equals(args[0], IS.P) ? "生产者" : StringUtils.equals(args[0], IS.C) ? "消费者" : StringUtils.equals(args[0], DEFAULT1) || StringUtils.equals(args[0], DEFAULT2) ? "生产者|消费者" : "";
    }

    public static final boolean product() {
        return StringUtils.contains(SERVICE, IS.P);
    }

    public static final boolean customer() {
        return StringUtils.contains(SERVICE, IS.C);
    }


}
