package com.algorithm.data.source;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadOnly {
    // 主从节点名称，默认只读节点为从节点
    String name() default "slave";
}
