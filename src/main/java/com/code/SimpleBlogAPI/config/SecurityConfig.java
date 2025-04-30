package com.code.SimpleBlogAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/blog/posts", "blog/posts/{id}").permitAll()
                        // Allowing H2 console to access
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated())

                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}