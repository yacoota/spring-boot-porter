package com.cacoota.porter.plugin.file;


import com.cacoota.porter.task.ITask;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component("custFile")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class FileCustomer extends AbstractFile implements ITask {

    private SFile file;

    private FileWriter fileWriter;

    // offer->添加一个元素并返回true   ：如果队列已满，则返回false
    // poll -> 移除并返问队列头部的元素：如果队列为空，则返回null
    // peek->返回队列头部的元素          ：如果队列为空，则返回null
    private Queue<String> data = new ConcurrentLinkedQueue<String>();

    @Getter
    @Setter
    private FileFilter  fileFilter;

    // 读取缓冲区（文件检查、文件新建、文件写入、文件关闭的集成）
    public void execute() {

    }


}
