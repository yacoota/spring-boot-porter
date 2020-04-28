package com.cacoota.porter.plugin.observe;

import com.cacoota.porter.plugin.strategy.IStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.Callable;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class FileCallable implements Callable<FileResult> {

    private IStrategy strategy;

    private String file;

    private String topic;

    @Override
    public FileResult call() throws Exception {
        strategy.execute(file, topic);
        return null;
    }
}
