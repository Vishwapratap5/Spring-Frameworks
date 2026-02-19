package com.guru.springsecurity_learning.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.guru.springsecurity_learning")
public class Configs {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
