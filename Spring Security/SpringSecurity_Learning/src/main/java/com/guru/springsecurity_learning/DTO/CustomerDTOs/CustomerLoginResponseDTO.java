package com.guru.springsecurity_learning.DTO.CustomerDTOs;

public record CustomerLoginResponseDTO(String token,
                                       String type,
                                       long expiresIn) {

}
