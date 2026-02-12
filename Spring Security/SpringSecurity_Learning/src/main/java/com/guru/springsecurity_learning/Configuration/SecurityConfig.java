package com.guru.springsecurity_learning.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(rcc->rcc.anyRequest().requiresSecure())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/account/**", "/myBalance/**", "/loans/**", "/cards/**").authenticated()
                        .requestMatchers("/","/contact/**", "/notices/**","/error/**","/api/register").permitAll()
                )
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
