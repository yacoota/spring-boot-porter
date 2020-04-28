package com.cacoota.porter.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

@Slf4j
public class ProcessUtil {

    // 计算当前number的取模值：即分配的进程数。
    private static final int calc(String number, int process) {
        int mod = new BigInteger(StringUtils.removePattern(number, "[a-z]")).mod(BigInteger.valueOf(process)).intValue() + 1;
        log.debug("进程配置信息->当前总进程数: {}", String.valueOf(process));
        log.info("进程配置信息->计算分配进程: {}", number + "\t" + mod);
        return mod;
    }

    public static final boolean isExecute(String number, int process, String currentProcess) {
        // boolean exe = StringUtils.contains(currentProcess, String.valueOf(calc(number, process)));
        boolean exe = StringUtils.contains(currentProcess, StringUtils.join(new String[]{":", number, ":"}));
        log.debug("进程配置信息->当前配置进程: {}", currentProcess);
        log.debug("当前进行是否运行此任务: {}", String.valueOf(exe));
        return exe;
    }
}
