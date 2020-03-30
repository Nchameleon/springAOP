package com.lwy.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

//������ advice(��ǿ)���һ������
//MethodInterceptor��������ͬ�ķ���,һ����cglib�����һ����aopalliance�����ʵ��spring���Զ�����
public class MyAspect implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("��������...");
        Object proceed = methodInvocation.proceed();
        System.out.println("��������...");
        return proceed;
    }
}
