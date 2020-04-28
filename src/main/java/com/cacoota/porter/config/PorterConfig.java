package com.cacoota.porter.config;

import com.cacoota.porter.config.base.FileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// 对外开放对象
@Component
public class PorterConfig {

    @Autowired
    private FileConfig config;

    public List<TaskConfig> product() {
        return config.getProduct();
    }

    public List<TaskConfig> customer() {
        return config.getCustomer();
    }

    public List<TaskConfig> all() {
        return config.getConfig();
    }
}
