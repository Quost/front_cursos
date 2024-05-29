package io.github.mqdev.front_cursos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> {
            auth
            .requestMatchers("/user/login").permitAll()
            .requestMatchers("/user/signin").permitAll()
            .requestMatchers("/user/register").permitAll()
            .anyRequest().authenticated();
        })
        .formLogin(form -> form.loginPage("/user/login"));
        return http.build();        
    }
}
