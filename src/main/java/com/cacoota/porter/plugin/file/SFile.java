package com.cacoota.porter.plugin.file;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;

@Data
@EqualsAndHashCode(callSuper = false)
// 自定义文件格式！！包含需要校验的数据内容！
public class SFile extends File {

    public SFile(String pathname) {
        super(pathname);
        this.size=0;
        this.line=0;
        this.time= System.currentTimeMillis();
    }

    private long size;

    private int line;

    private long time;

}
