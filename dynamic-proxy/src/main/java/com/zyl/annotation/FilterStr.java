package com.zyl.annotation;

import java.lang.annotation.*;

/**
 * @author zhangyanlong
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FilterStr {

    String value();
}
