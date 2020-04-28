package com.cacoota.framework.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

@Slf4j
public class IC {
    // 应用程序配置信息
    private static final String CFILE = "config/application.properties";

    public static Configuration P;

    // 全局配置文件初始信息
    public static String GLOBAL_PROFILE_CONFIG = null;
    public static String GLOBAL_PROFILE_ZOO_KEY = null;

    // 全局任务配置信息
    public static String GLOBAL_TASK_CONFIG = null;

    // 数据库登录信息
    public static String GLOBAL_JDBC_DRIVER = null;

    // ZOO配置信息
    public static String GLOBAL_ZOO_CONFIG = null;

    // 当前进程运行的命令
    public static String GLOBAL_EXECUTE_CURRENT_PROCESS = null;


    // 初始手工加载应用程序配置，自行处理配置应用！注意顺序
    static {
        try {
            P = new PropertiesConfiguration(CFILE);
            GLOBAL_PROFILE_CONFIG = P.getString("global-config-file");
            GLOBAL_PROFILE_ZOO_KEY = P.getString("global-zookeeper-key");
            GLOBAL_TASK_CONFIG = P.getString("global-task-file");
            GLOBAL_JDBC_DRIVER = P.getString("global-db-jdbc-driver");
            if(StringUtils.isNotEmpty(System.getProperty("execute.task"))) {
                GLOBAL_EXECUTE_CURRENT_PROCESS = System.getProperty("execute.task").trim();
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    // 加载ZOO相关配置和其他配置
    public synchronized static final void load() {
        GLOBAL_ZOO_CONFIG = Tools.GLOBAL_ZOO_CONFIG;
    }

}
