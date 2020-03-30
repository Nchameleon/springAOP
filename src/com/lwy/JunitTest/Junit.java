package com.lwy.JunitTest;

import com.lwy.service.IUserService;
import com.lwy.factory.MyBeanFactory;
import com.lwy.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Junit {
    @Test
    public  void  aopTest(){
        MyBeanFactory factory = new MyBeanFactory();
        IUserService iUserService = factory.createFactory();
        iUserService.addUser();
    }

    @Test
    public  void  aopTest2(){
        MyBeanFactory factory = new MyBeanFactory();
        StudentService studentService = factory.createFactory2();
        studentService.sleep();
    }

    @Test
    public void aopTest3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        IUserService iUserService = (IUserService) context.getBean("UserProxy");
        iUserService.addUser();
    }

    @Test
    public void aopTest4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        IUserService iUserService = (IUserService) context.getBean("userService");
        StudentService studentService = (StudentService) context.getBean("studentService");
        iUserService.addUser();
        studentService.study();
    }
    @Test
    public void aopTest5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        IUserService iUserService = (IUserService) context.getBean("userService");
        iUserService.addUser();
    }
}
