package com.nowcoder.community.controller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//自定义注解，用元注解来写
//target表示可以作用在方法上，还是在类上
@Target(ElementType.METHOD)
//retention表示自己的有效时间
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

}
