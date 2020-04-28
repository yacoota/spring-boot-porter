package com.cacoota.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("cc")
public class Configuration {

    @Autowired
    private Environment env;

    public String properties(String name) {
        return env.getProperty(name);
    }

}
