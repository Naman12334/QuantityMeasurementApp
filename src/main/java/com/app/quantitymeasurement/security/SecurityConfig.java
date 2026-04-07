package com.app.quantitymeasurement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CustomOAuth2SuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ disable csrf for APIs
            .csrf(csrf -> csrf.disable())

            // ❌ disable default login form (important)
            .formLogin(form -> form.disable())

            // ❌ disable http basic
            .httpBasic(basic -> basic.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/h2-console/**",

                    // ✅ OAuth endpoints
                    "/oauth2/**",
                    "/login/**",

                    // ✅ allow your APIs for frontend (optional)
                    "/api/**"
                ).permitAll()

                .anyRequest().authenticated()
            )

            // ✅ GOOGLE LOGIN
            .oauth2Login(oauth -> oauth
                .successHandler(successHandler)
            )

            // ✅ JWT FILTER (after OAuth config)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            // ✅ H2 console fix
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}