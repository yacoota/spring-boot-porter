package com.cacoota.porter.plugin.strategy;

public interface IStrategy {
    // 文件到话题
    void execute(String file, String topic);
}
