package com.guru.springsecurity_learning.Service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidAccountNumberGenerator implements AccountNumberGenerationService {
    @Override
    public String generateAccountNumber() {
        return "AC-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}
