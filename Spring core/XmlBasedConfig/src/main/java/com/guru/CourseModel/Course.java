package com.guru.CourseModel;

import lombok.*;

@ToString
@NoArgsConstructor
@Data
public class Course {
    private int courseID;
    private String courseName;
    private String courseDescription;
    private String courseDuration;

    void showDetails(){
        System.out.println(courseID+" "+courseName+" "+courseDescription+" "+courseDuration);
    }
}
