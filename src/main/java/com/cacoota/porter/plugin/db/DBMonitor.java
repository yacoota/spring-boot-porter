package com.cacoota.porter.plugin.db;

public @interface DBMonitor {

    boolean monitor() default true;

}
