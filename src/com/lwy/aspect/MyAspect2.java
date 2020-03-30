package com.lwy.aspect;

public class MyAspect2 {
    public void before(){
        System.out.println("事物开启...");
    }

    public void after(){
        System.out.println("关闭事物...");
    }
}
