package com.guru.springsecurity_learning.Configuration;

import com.guru.springsecurity_learning.Filter.JwtValidationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    CorsConfigurationSource corsConfigurationSource;


    @Autowired
    JwtValidationFilter jwtValidationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors->cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/account/**", "/myBalance/**", "/loans/**", "/cards/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/super-admin/panel/**").hasRole("SUPER_ADMIN")
                        .requestMatchers("/admin/accounts/**").hasAnyRole("ADMIN","MANAGER")
                        .requestMatchers(
                                "/",
                                "/contact/**",
                                "/notices/**",
                                "/error/**",
                                "/api/auth/register",
                                "/api/auth/login",
                                "/public/**"

                        ).permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtValidationFilter, SecurityContextHolderFilter.class)
                .exceptionHandling(ex->
                        ex
                        .authenticationEntryPoint((request, response, authException) -> {
                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                    response.setContentType("application/json");
                                    response.getWriter().write("{\"message\":\"Please login\"}");
                        }
                        ).accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                    response.setContentType("application/json");
                                    response.getWriter().write("{\"message\":\"Please login\"}");
                        })
                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    AuthenticationManager  authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
