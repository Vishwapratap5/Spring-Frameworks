package com.guru.Service;

import com.guru.DAO.StudentDAO;
import com.guru.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentDAO studentDAO;
    @Autowired
    public StudentService(StudentDAO studentDAO){
        this.studentDAO=studentDAO;

    }
    public Boolean addStudentService(Student student) {
        return studentDAO.addStudent(student);
    }

    public Boolean deleteStudentService(Student student) {
        return studentDAO.deleteStudent(student);
    }
}
