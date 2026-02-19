package com.guru.springsecurity_learning.Configuration;

import com.guru.springsecurity_learning.Filter.JwtCreationFilter;
import com.guru.springsecurity_learning.Filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ResponseEntityExceptionHandler responseEntityExceptionHandler, JwtValidationFilter jwtValidationFilter, JwtCreationFilter jwtCreationFilter) throws Exception {
        http
                .cors(cors->cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/account/**", "/myBalance/**", "/loans/**", "/cards/**").authenticated()
                        .requestMatchers("/","/contact/**", "/notices/**","/error/**","/api/register").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                )
                .oauth2Login(Customizer.withDefaults())
                .addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtCreationFilter,UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex->
                        ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendError(401);
                            response.setContentType("application/json");
                            response.getWriter().write("Please login ..!");
                        }
                        ).accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.sendError(403);
                                    response.setContentType("application/json");
                                    response.getWriter().write("You don't have permission to access this resource..!");
                        })
                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
