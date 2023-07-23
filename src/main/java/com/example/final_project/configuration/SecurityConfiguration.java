package com.example.final_project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
    @EnableWebSecurity
    public class SecurityConfiguration {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(requiest ->
                            requiest
                                    .requestMatchers("/admin/**").hasRole("ADMIN")
                                    .requestMatchers("/student/**").hasRole("STUDENT")
                                    .requestMatchers("/professor/**").hasRole("PROFESSOR")
                                    .anyRequest().permitAll()

                    )
                    .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                    .logout(LogoutConfigurer::permitAll)
                    .build();
        }



}
