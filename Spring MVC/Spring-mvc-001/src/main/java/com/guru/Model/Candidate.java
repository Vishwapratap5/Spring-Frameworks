package com.guru.Model;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Component
public class Candidate {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
}
