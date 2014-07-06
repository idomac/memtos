package com.quanix.memtos.server.web.bind;

import java.lang.annotation.*;

/**
 * created by lihaoquan
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageableDefaults {

    int value() default 10;

    int pageNumber() default 0;

    /**
     * 默认的排序 格式为{"a=desc, a.b=desc"}
     */
    String[] sort() default {};
}
