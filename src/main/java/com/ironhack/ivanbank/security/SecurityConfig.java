package com.ironhack.ivanbank.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JpaUserDetailService jpaUserDetailService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()

                .requestMatchers(HttpMethod.POST,"/transaction/deposit").permitAll()

                // USER ENDPOINTS
                .requestMatchers(HttpMethod.PATCH,"/user/password").hasRole("USER")
                .requestMatchers(HttpMethod.POST,"/transaction/withdraw").hasRole("USER")
                .requestMatchers(HttpMethod.POST,"/transaction/transfer").hasRole("USER")
                .requestMatchers(HttpMethod.GET,"/transaction/all").hasRole("USER")

                // ADMIN ENDPOINTS
                .requestMatchers(HttpMethod.POST,"/account-holder/create").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/account-holder/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH,"/user/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/checking-account/create-default").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/checking-account/create-custom").hasRole("ADMIN")

                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(jpaUserDetailService)
                .httpBasic()
                .and()
                .build();
    }
}
