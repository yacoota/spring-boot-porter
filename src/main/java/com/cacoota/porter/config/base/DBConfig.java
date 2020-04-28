package com.cacoota.porter.config.base;

import com.cacoota.porter.config.TaskConfig;
import com.cacoota.porter.plugin.db.IDB;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("dbconfig")
public class DBConfig extends AbstractConfig implements IDB {

    @Getter
    private List<TaskConfig> config = new ArrayList<>();

    @Getter
    private List<TaskConfig> product = new ArrayList<>();

    @Getter
    private List<TaskConfig> customer = new ArrayList<>();

    @Override
    public void connect() {

    }
}
