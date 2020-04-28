package com.cacoota.porter.config;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class TaskConfig {

    // 配置主键信息：取模分配服务器
    @JSONField(name = "uuid", ordinal = 1)
    private String uuid;

    @JSONField(name = "topic", ordinal = 2)
    private String topic;

    @JSONField(name = "paths", ordinal = 3)
    private List<String> paths;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "key", ordinal = 5)
    private String key;

    @JSONField(name = "type", ordinal = 6)
    private String type;

    @JSONField(name = "group", ordinal = 7)
    private String group;


    @JSONField(name = "ticket", ordinal = 8)
    private String ticket;

    @JSONField(name = "msgs", ordinal = 9)
    private List<String> msgs;
}
