package com.lwy.factory;

import com.lwy.aspect.MyAspect1;
import com.lwy.service.IUserService;
import com.lwy.service.StudentService;
import com.lwy.service.UserService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ����jdk��̬����ֻ�������нӿڵ����� proxy
 */
public class MyBeanFactory {
    //�Լ�ʵ��һ��������
    //��һ������������ʵ��IUserService������UserService
    public static IUserService createFactory(){
        //����Ŀ�����target
        //jdk1.8��ǰ�İ汾�ڲ�����ó�Ա������ʱ����Ҫ�ڳ�Ա������ǰ���final
       IUserService userService = new UserService();

        //�Լ�����һ������
        MyAspect1 aspect = new MyAspect1();

        //�����������������Ӧ�õ�Ŀ����
            /*
                loader:
                ClassLoader ����������ر�����

                interfaces: ������Ҫ��ǿ���Ľӿ�
                Class<?>[]

                h:
                InvocationHandler �ӿڴ���
            * */
        //ʹ�ô���,ʹ��JDK��̬����proxy
        IUserService serviceProxy = (IUserService) Proxy.newProxyInstance(MyBeanFactory.class.getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        aspect.before();
                        //�����ķ���ֵ��ҵ�񷽷��ķ���ֵ
                        Object invoke = method.invoke(userService, args);
                        System.out.println("���صķ���ֵ"+invoke);
                        aspect.after();
                        return invoke;
                    }
                });

        return serviceProxy;
    }
    public static StudentService createFactory2(){
        //����Ŀ�����target
        //ʹ��cglib�ֽ�����ǿ ���޽ӿڶ�����ʵ��
        final StudentService studentService = new StudentService();

        MyAspect1 aspect = new MyAspect1();

        //������ǿ����
        Enhancer enhancer = new Enhancer();
        //���ø������
        enhancer.setSuperclass(studentService.getClass());
        //���ûص�[������һ��]
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                    throws Throwable {
                aspect.before();
                //o��StudentService
                //System.out.println(o.getClass());
                //����ǿ��Ĵ�����
                //System.out.println(methodProxy);
               // Object invoke = method.invoke(studentService, objects);
                Object invoke = methodProxy.invokeSuper(o,objects);//����,o���ڸ�������ʱ���ɵ�����
                System.out.println("���صķ���ֵ"+invoke);
                aspect.after();
                return invoke;
            }
        });
        StudentService studentServices = (StudentService) enhancer.create();
        return studentServices;
    }
}
