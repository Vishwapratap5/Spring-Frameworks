package com.guru.CourseModel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Course {
    private int courseID;
    private String courseName;
    private String courseDescription;
    private String courseDuration;
}
