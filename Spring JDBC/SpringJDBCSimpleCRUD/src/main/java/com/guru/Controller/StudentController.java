package com.guru.Controller;

import com.guru.Model.Student;
import com.guru.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {
    StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    public void addStudent(Student student){
//
        student.setStudentID(1);
        student.setStudentName("Vijayraj");
        student.setAge(20);
        student.setMobileNum("123456789");


        Boolean isAdded=studentService.addStudentService(student);
        if(isAdded){
            System.out.println("Student added successfully");
        }else{
            System.out.println("Student not added ");
        }
    }

    public void deleteStudent(Student student){
        student.setStudentID(2);
        Boolean isAdded=studentService.deleteStudentService(student);
        if(isAdded){
            System.out.println("Student Deleted successfully");
        }else{
            System.out.println("Student not Deleted");
        }

    }
}
