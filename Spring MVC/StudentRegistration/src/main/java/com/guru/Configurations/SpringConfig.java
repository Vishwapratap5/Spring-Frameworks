package com.guru.Configurations;

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
@PropertySource("classpath:applications.properties")
public class SpringConfig {
    Environment env;
    public SpringConfig(Environment environment) {
        this.env = environment;
    }
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("mysql.db.DriverClass"));
        ds.setUrl(env.getProperty("mysql.db.URL"));
        ds.setUsername(env.getProperty("mysql.db.USER"));
        ds.setPassword(env.getProperty("mysql.db.PASSWORD"));
        return ds;

    }
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
