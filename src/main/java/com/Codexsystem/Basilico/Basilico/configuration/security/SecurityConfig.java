package com.Codexsystem.Basilico.Basilico.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/refeicao/criar/refeicao").hasRole("ADMIN")
                        .requestMatchers("/refeicao/obter/refeicao").permitAll()
                        .requestMatchers("/refeicao/obter/{id}").permitAll()
                        .requestMatchers("/bebida/criar/bebida").hasRole("ADMIN")
                        .requestMatchers("/bebida/obter/bebida").permitAll()
                        .requestMatchers("/bebida/obter/{id}").permitAll()
                        .requestMatchers("/pedidos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/pedidos/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/pedidos/criar").hasRole("USER")
                        .anyRequest().authenticated()
                ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
