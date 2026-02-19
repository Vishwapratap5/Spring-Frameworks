package com.guru.springsecurity_learning.DTO.ContactDTOs;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCreateContactDTO {

    @NotBlank
    private String contactName;

    @Email
    @NotBlank
    private String contactEmail;

    @NotBlank
    private String subject;

    @NotBlank
    private String message;
}
