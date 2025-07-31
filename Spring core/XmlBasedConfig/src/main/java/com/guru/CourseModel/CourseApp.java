package com.guru.CourseModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CourseApp {

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("Spring.config.xml");
        Course course1 =context.getBean("course",Course.class);
        System.out.println(course1);
    }
}
