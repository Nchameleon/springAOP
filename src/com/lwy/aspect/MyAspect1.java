package com.lwy.aspect;

//切入点和 advice(增强)组成一个切面
public class MyAspect1 {
    public void before(){
        System.out.println("事物开启...");
    }

    public void after(){
        System.out.println("关闭事物...");
    }
}
