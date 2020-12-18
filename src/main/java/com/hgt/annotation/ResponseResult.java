package com.hgt.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

/*第一步：自定义的注解类，对接口返回值统一包装*/
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
