package com.guru.CourseModel;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//@AllArgsConstructor
//@NoArgsConstructor
@Data
//@Builder
@ToString
@Component("course1")
public class Course {
//    @Value("100")
//@Value("${courseID}")
    private int courseID;
//    @Value("Java Full Stack")
//@Value("${courseName}")
    private String courseName;
//    @Value("Begginer friendly")
//@Value("${courseDescription}")
    private String courseDescription;
//    @Value("6 Months")
//@Value("${courseDuration}")
    private String courseDuration;

    @Autowired
    public Course(Environment environment){
        this.courseID = Integer.parseInt(environment.getProperty("courseID"));
        this.courseName = environment.getProperty("courseName");
        this.courseDescription = environment.getProperty("courseDescription");
        this.courseDuration = environment.getProperty("courseDuration");
    }
}
