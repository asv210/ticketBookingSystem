package com.darkSProject.ticketBooking.config;

import com.darkSProject.ticketBooking.jwt.JwtAuthenticationEntryPoint;
import com.darkSProject.ticketBooking.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter){
        http.csrf(csrf->csrf.disable())
                        .sessionManagement(session->
                                session.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                        )
                .exceptionHandling(
                        ex ->
                                ex.authenticationEntryPoint(
                                        jwtAuthenticationEntryPoint
                                )
                )
                        .authorizeHttpRequests(auth->auth
                        .requestMatchers(
                                "/auth/**",
                                "/error"
                        ).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }
}
