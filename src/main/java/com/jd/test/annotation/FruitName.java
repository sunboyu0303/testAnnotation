package com.jd.test.annotation;

import java.lang.annotation.*;

/**
 * 水果名称注解
 * <p>
 * Created by sunboyu on 2017/9/25.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {

    String value() default "";
}
