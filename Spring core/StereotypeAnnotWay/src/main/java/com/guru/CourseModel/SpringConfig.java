package com.guru.CourseModel;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.guru")
@PropertySource("classpath:CourseApp.properties")
public class SpringConfig {
}
