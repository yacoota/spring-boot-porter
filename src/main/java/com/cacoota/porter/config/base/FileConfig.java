package com.cacoota.porter.config.base;

import com.alibaba.fastjson.JSON;
import com.cacoota.framework.common.C;
import com.cacoota.framework.common.Tools;
import com.cacoota.porter.config.TaskConfig;
import com.cacoota.porter.plugin.file.IFile;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("fileconfig")
public class FileConfig extends AbstractConfig implements IFile {

    @Getter
    private List<TaskConfig> config = new ArrayList<>();

    @Getter
    private List<TaskConfig> product = new ArrayList<>();

    @Getter
    private List<TaskConfig> customer = new ArrayList<>();

    public void parse() {
        List<String> results = Tools.parse();
        for (String result : results) {
            TaskConfig task = JSON.parseObject(result, TaskConfig.class);
            /** 生成随机数字模拟测试 **/
            task.setUuid(RandomStringUtils.random(4, false, true));
            task.setTicket(RandomStringUtils.random(20, true, true).toUpperCase());
            task.setGroup(RandomStringUtils.random(1, false, true));

            if (StringUtils.equals(task.getType(), C.PRODUCT_TYPE)) {
                task.setType(task.getType());
                product.add(task);
            } else if (StringUtils.equals(task.getType(), C.CUSTOMER_TYPE)) {
                customer.add(task);
            }
            config.add(task);
        }
    }


}
