package com.cacoota.framework.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class Tools {

    public static String GLOBAL_ZOO_CONFIG = null;


    static {
        try {
            GLOBAL_ZOO_CONFIG = Tools.parseProfile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取全局PROFILE的配置！
    public static final String parseProfile() throws IOException {
        String result = null;
        List<String> list = FileUtils.readLines(new File(SystemUtils.getUserHome().getPath().concat("/").concat(IC.GLOBAL_PROFILE_CONFIG)), CharEncoding.UTF_8);
        for (String data : list) {
            if (!StringUtils.startsWith(StringUtils.trim(data), "#") && StringUtils.contains(data, IC.GLOBAL_PROFILE_ZOO_KEY)) {
                result = StringUtils.substring(data, StringUtils.indexOf(data, "=") + 1).trim();
                break;
            }
        }
        return result;
    }



    // 获取配置文件中的配置！
    public static final List<String> parse()  {
        try {
            return FileUtils.readLines(new File(IC.GLOBAL_TASK_CONFIG));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
