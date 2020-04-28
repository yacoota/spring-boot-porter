package com.cacoota.porter.task;


public @interface TaskMonitor {

    boolean monitor() default true;

}
