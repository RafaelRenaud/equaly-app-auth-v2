package com.br.equaly.auth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig{

    private static final String[] systemAllowedEndpoints = new String[]{
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/actuator/**",
            "/**"
    };

    @Bean
    public SecurityFilterChain generalSecurityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.GET, systemAllowedEndpoints).permitAll();
                    auth.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
