package com.guru.Service;

import com.guru.DAO.StudentDAO;
import com.guru.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    StudentDAO studentDAO;
    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Student getStudentByID(int id) {
        return studentDAO.getStudent(id);
    }

    public void saveStudent(Student student) {
         studentDAO.addStudent(student);
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }
}
