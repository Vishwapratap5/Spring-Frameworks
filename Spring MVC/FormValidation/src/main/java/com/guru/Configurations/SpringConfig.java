package com.guru.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.guru")
@PropertySource("classpath*:applications.properties")
public class SpringConfig {
    private Environment environment;
    @Autowired
    public SpringConfig(Environment environment) {
        this.environment = environment;
    }
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        ds.setUrl(environment.getProperty("spring.datasource.URL"));
        ds.setUsername(environment.getProperty("spring.datasource.USERNAME"));
        ds.setPassword(environment.getProperty("spring.datasource.PASSWORD"));
        return ds;
    }
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
