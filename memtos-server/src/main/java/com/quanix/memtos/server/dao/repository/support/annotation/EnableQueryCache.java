package com.quanix.memtos.server.dao.repository.support.annotation;

import java.lang.annotation.*;

/**
 * created by lihaoquan
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableQueryCache {

    boolean value() default true;
}
