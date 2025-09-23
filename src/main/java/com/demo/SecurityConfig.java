package com.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // 全てのリクエストを許可
            )
            .formLogin(form -> form
                    .loginPage("/login/login")   // 自作ログイン画面
                    .permitAll()
                )
//            .csrf(csrf -> csrf.disable()); // 開発用にCSRF無効化（必要なら）
            ;
        return http.build();
    }
}
