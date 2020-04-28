package com.cacoota.porter.plugin.file;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class FileFilter extends AbstractFileFilter {

    public List<IFileFilter> filters() {
        return Arrays.asList(new IFileFilter[]{new FileLineFilter(), new FileSizeFilter(), new FileTimeFilter()});
    }

    // 文件行数过滤器
    class FileLineFilter implements IFileFilter {
        @Override
        public boolean filter(SFile file) {
            return true;
        }
    }

    // 文件大小过滤器
    class FileSizeFilter implements IFileFilter {
        @Override
        public boolean filter(SFile file) {
            return true;
        }
    }

    // 文件超时过滤器
    class FileTimeFilter implements IFileFilter {
        @Override
        public boolean filter(SFile file) {
            return true;
        }

    }

}
