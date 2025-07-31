package com.guru.Model;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Student {
    private int studentID;
    private String studentName;
    private int age;
    private String mobileNum;
}
