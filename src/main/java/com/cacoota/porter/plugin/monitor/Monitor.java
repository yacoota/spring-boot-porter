package com.cacoota.porter.plugin.monitor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Monitor {

    boolean monitor() default true;

}
