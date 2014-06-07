package com.quanix.memtos.server.web.bind;

import com.quanix.memtos.server.util.Constants;

import java.lang.annotation.*;

/**
 * @author : lihaoquan
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
    String value() default Constants.CURRENT_USER;
}
