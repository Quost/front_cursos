package io.github.mqdev.front_cursos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers("/login").permitAll()
            .anyRequest().authenticated();
        })
        .formLogin(form -> form.loginPage("/login"));
        return http.build();        
    }
}
