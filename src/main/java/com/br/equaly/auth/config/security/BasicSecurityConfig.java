package com.br.equaly.auth.config.security;

import com.br.equaly.auth.config.security.impl.BasicAuthenticationFilterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(2)
public class BasicSecurityConfig {

    private static final String[] basicPostEndpoints = new String[]{
            "/login",
            "/recovery"
    };

    private static final String[] basicPathEndpoints = new String[]{
            "/recovery/**"
    };

    @Bean
    public SecurityFilterChain basicSecurityFilterChain(HttpSecurity http, BasicAuthenticationFilterConfig basicFilter) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, basicPostEndpoints).authenticated();
                    auth.requestMatchers(HttpMethod.PATCH, basicPathEndpoints).authenticated();
                })
                .addFilterBefore(basicFilter, BasicAuthenticationFilter.class)
                .build();
    }
}
