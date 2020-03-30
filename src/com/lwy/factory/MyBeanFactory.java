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
 * 这种jdk动态代理只能用在有接口的类上 proxy
 */
public class MyBeanFactory {
    //自己实现一个代理类
    //在一个工厂类里面实现IUserService来调用UserService
    public static IUserService createFactory(){
        //创建目标对象target
        //jdk1.8以前的版本内部类调用成员变量的时候需要在成员变量的前面加final
       IUserService userService = new UserService();

        //自己创建一个切面
        MyAspect1 aspect = new MyAspect1();

        //把切面类的两个方法应用到目标类
            /*
                loader:
                ClassLoader 类加载器加载本地类

                interfaces: 拦截需要增强出的接口
                Class<?>[]

                h:
                InvocationHandler 接口处理
            * */
        //使用代理,使用JDK动态代理proxy
        IUserService serviceProxy = (IUserService) Proxy.newProxyInstance(MyBeanFactory.class.getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        aspect.before();
                        //方法的返回值是业务方法的返回值
                        Object invoke = method.invoke(userService, args);
                        System.out.println("拦截的返回值"+invoke);
                        aspect.after();
                        return invoke;
                    }
                });

        return serviceProxy;
    }
    public static StudentService createFactory2(){
        //创建目标对象target
        //使用cglib字节码增强 有无接口都可以实现
        final StudentService studentService = new StudentService();

        MyAspect1 aspect = new MyAspect1();

        //创建增强对象
        Enhancer enhancer = new Enhancer();
        //设置父类对象
        enhancer.setSuperclass(studentService.getClass());
        //设置回调[和拦截一样]
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                    throws Throwable {
                aspect.before();
                //o是StudentService
                //System.out.println(o.getClass());
                //是增强类的代理方法
                //System.out.println(methodProxy);
               // Object invoke = method.invoke(studentService, objects);
                Object invoke = methodProxy.invokeSuper(o,objects);//解耦,o是在父类运行时生成的子类
                System.out.println("拦截的返回值"+invoke);
                aspect.after();
                return invoke;
            }
        });
        StudentService studentServices = (StudentService) enhancer.create();
        return studentServices;
    }
}
