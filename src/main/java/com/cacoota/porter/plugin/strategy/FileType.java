package com.cacoota.porter.plugin.strategy;

import lombok.Getter;
import lombok.Setter;

public enum FileType {

    DAT("正常", 1), RUN("处理", 2), EXIT("完成", 3), LOG("日志", 4);

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int _id;


    // 构造方法
    private FileType(String name, int _id) {
        this.name = name;
        this._id = _id;
    }

    public static FileType getFileType(int index) {
        switch (index) {
            case 2:
                return FileType.RUN;
            case 3:
                return FileType.EXIT;
            case 4:
                return FileType.LOG;
            default:
                return FileType.DAT;
        }
    }

}
