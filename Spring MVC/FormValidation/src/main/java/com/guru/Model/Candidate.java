package com.guru.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Candidate {
    private int id;
    @NotBlank(message="Name is required")
    private String name;
    @Email(message = "Please fill this field")
    @NotBlank(message="Email is required")
    private String email;
    @NotBlank(message ="phone number is required" )
    private String phone;
    @NotBlank(message ="Address is required" )
    private String address;
}
