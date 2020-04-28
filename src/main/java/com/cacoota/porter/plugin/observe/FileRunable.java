package com.cacoota.porter.plugin.observe;

import com.cacoota.porter.plugin.strategy.IStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class FileRunable implements Runnable {

    private IStrategy strategy;

    private String file;

    private String topic;

    @Override
    public void run() {
        strategy.execute(file, topic);
    }
}
