package com.guru.Model;

import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@ToString
@Builder
public class Student {

    private int id;
    private String name;
    private String email;
    private String courseName;
}
