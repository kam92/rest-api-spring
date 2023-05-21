package com.camera.schoolsystem.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] AUTH_WHITELIST = {

            // for Swagger UI v3 (OpenAPI)
            "/docs",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "configuration/**",
            "/webjars/**",
            //home page
            "/",
            "/home"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                ).formLogin(Customizer.withDefaults()).csrf().disable()
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

}