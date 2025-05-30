package com.code.SimpleBlogAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // created the security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                // configuring the security
                .authorizeHttpRequests(auth -> auth
                        // allowing the people to view posts without any authentication
                        .requestMatchers("/blog/posts", "/blog/posts/{id}").permitAll()

                        // allowed to register and login the user
                        .requestMatchers("/blog/register", "/blog/login").permitAll()

                        // allowing only the user to create, edit and delete posts
                        .requestMatchers("/blog/dashboard", "/blog/posts/create", "/blog/posts/edit/{id}",
                                "/blog/posts/delete/{id}")
                        .hasRole("USER")

                        // Allowing H2 console to access
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated())

                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // created the authentication manager
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
            BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    //
    @Bean
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    // created the user details service for creating in memory users
    @Bean
    public JdbcUserDetailsManager userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // if (!manager.userExists("user")) {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("adpass"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("pass"))
                .roles("USER")
                .build();
        manager.createUser(admin);
        manager.createUser(user);
        // }
        return manager;
    }

    // created the password encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}