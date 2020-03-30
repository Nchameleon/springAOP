package com.lwy.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

//切入点和 advice(增强)组成一个切面
//MethodInterceptor有两个相同的方法,一个是cglib里面的一个是aopalliance里面的实现spring半自动代理
public class MyAspect implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("开启事物...");
        Object proceed = methodInvocation.proceed();
        System.out.println("结束事物...");
        return proceed;
    }
}
