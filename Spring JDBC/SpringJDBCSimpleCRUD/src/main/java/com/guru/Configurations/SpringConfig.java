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
@PropertySource("classpath:Application.properties")
public class SpringConfig {
    @Autowired
    Environment environment;
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getProperty("mysql.db.DriverName"));
        ds.setUrl(environment.getProperty("mysql.db.URL"));
        ds.setUsername(environment.getProperty("mysql.db.USER"));
        ds.setPassword(environment.getProperty("mysql.db.PASSWORD"));
        return ds;
    }
    @Bean
    public JdbcTemplate  jdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
