package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//指定这个注解在哪生效
@Retention(RetentionPolicy.RUNTIME)//指定这个注解何时生效
public @interface Log {
    //定义的注解无需定义任何属性，起标识方法的作用
}
