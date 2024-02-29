package com.nowcoder.community.controller.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {
//    设置切点,第一个星号表示返回值的类型,第二个星表示这个包下的所有类, 第三个星表示所有方法,(..)表示所有参数,表示所有service的组件每次调用都需要处理,
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
//    在切点之前织入
    public void before() {
        System.out.println("before");
    }
    //    在切点之后织入
    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }
    //    在返回值之后织入
    @AfterReturning("pointcut()")
    public void afterreturning() {
        System.out.println("afterreturning");
    }
//    在异常后织入
    @AfterThrowing("pointcut()")
    public void afterthrowing() {
        System.out.println("afterthrowing");
    }
//    在前后都织入
    @Around("pointcut()")
//    传入的参数为连接点,在连接点前后织入
    public Object around(ProceedingJoinPoint joinPoint) throws  Throwable {
        System.out.println("around before");
        Object obj = joinPoint.proceed();
        System.out.println("around after");
        return obj;
    }

}
