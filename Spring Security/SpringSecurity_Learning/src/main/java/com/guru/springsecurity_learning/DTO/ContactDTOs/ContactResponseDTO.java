package com.guru.springsecurity_learning.DTO.ContactDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactResponseDTO {

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

    private LocalDateTime createdAt;
}

