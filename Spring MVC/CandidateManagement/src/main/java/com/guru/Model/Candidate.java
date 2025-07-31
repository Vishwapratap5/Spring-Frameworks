package com.guru.Model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    private int id;
    private String name;
    private String email;
    private String mobile;
    private String address;
}
