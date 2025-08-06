package com.guru.Controller;

import com.guru.Model.Student;
import com.guru.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class StudentController {
    StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/form")
    public ModelAndView registrationForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("registrationForm");
        return mv;
    }

    @PostMapping("/register")
    public String registrationSubmit(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "Success";
    }

    @GetMapping("/getIdForm")
    public ModelAndView idForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("idForm");
        return mv;
    }

    @GetMapping("/student")
    public ModelAndView student(@RequestParam("id") int id) {
        ModelAndView mv = new ModelAndView();
        Student student=studentService.getStudentByID(id);
        System.out.println(student);
        mv.addObject("student",student);
        mv.setViewName("studentDetails");
        return mv;
    }

    @GetMapping("/getUpdateCourseForm")
    public String getUpdateCourseForm() {
        return "updateCourseForm";
    }

    @PutMapping("/updateCourseForm")
    public ModelAndView updateCourseForm(@ModelAttribute("student") Student student) {
        ModelAndView mv = new ModelAndView();
        studentService.updateStudent(student);
        return mv;
    }
}
