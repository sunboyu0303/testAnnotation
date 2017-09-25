package com.jd.test.annotation;

import java.lang.annotation.*;

/**
 * 水果颜色注解
 * <p>
 * Created by sunboyu on 2017/9/25.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    /**
     * 颜色枚举
     */
    enum Color {
        GREEN, RED, YELLOW
    }

    /**
     * 颜色属性
     *
     * @return
     */
    Color fruitColor() default Color.GREEN;
}
