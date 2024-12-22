package com.namayatri.namayatri.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.stereotype.Component;

@Configuration
public class SecurityConfig {
    private final JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        //hcd^2

        http.csrf(csrf->csrf.disable()).cors(cors->cors.disable());

        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        //haap

        http.authorizeHttpRequests(auth->auth.anyRequest().permitAll());

        return http.build();

    }
}
