package com.stagedriving.modules.commons.interceptor.restricted;

import com.stagedriving.commons.StgdrvData;

import java.lang.annotation.*;

/**
 * Created by simone on 29/07/14.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD })
public @interface Restricted {
    /**
     * If {@code true}, the request will not be processed in the absence of a valid principal. If
     * {@code false}, {@code null} will be passed in as a principal. Defaults to {@code true}.
     */
    boolean required() default true;

    String role() default StgdrvData.AccountRoles.USER;

    boolean onlyActivated() default true;
}

