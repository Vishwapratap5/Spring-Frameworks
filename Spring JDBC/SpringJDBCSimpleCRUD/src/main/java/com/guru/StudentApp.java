package com.guru;

import com.guru.Controller.StudentController;
import com.guru.Model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.guru.Configurations");
        StudentController studentController = context.getBean(StudentController.class);
        Student student = context.getBean(Student.class);
        studentController.deleteStudent(student);
    }
}
