package com.ticy.manage.myannotation;

import java.lang.annotation.*;

/**
 * @Author tkk
 * @Time 2021/8/16 18:05
 * @Description todo
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    /**
     * 要执行的操作类型比如：add操作
     **/
    public String operationType() default "";

    /**
     * 要执行的具体操作比如：添加用户
     **/
    public String operationName() default "";
}
