package com.soft_arex.security;

import com.soft_arex.repository.UserRepository;
import com.soft_arex.security.filters.CustomAccessDeniedHandler;
import com.soft_arex.security.filters.JwtAuthenticationFilter;
import com.soft_arex.security.filters.RestAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class JwtSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(restAuthenticationEntryPoint))
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(
                                        "/", "/index.html", "/favicon.ico",
                                        "/static/**", "/manifest.json",
                                        "/asset-manifest.json",
                                        "/css/**", "/js/**", "/img/**"
                                ).permitAll()




                                .requestMatchers("/ws").permitAll()
                                .requestMatchers("/ws/**","/topic/**").permitAll()
                                .requestMatchers("/oauth/sign-up").permitAll()
                                .requestMatchers("/oauth/sign-in").permitAll()
                                .requestMatchers("/oauth/refresh-token").permitAll()
                                .requestMatchers("/oauth/me").authenticated()
                                .requestMatchers(HttpMethod.GET,"/fields").permitAll()
                                .requestMatchers(HttpMethod.POST,"/fields").authenticated()
                                .requestMatchers(HttpMethod.PUT,"/fields/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE,"/fields").authenticated()
                                .requestMatchers("/answer").permitAll()
                                .requestMatchers(HttpMethod.GET,"/answer/**").authenticated()
                                .requestMatchers(HttpMethod.GET,"/test","/test/all","/questionnaires/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/test").authenticated()
                                .requestMatchers(HttpMethod.PATCH,"/test/{id}/status").authenticated()
                                .requestMatchers(HttpMethod.PUT,"/test/**").authenticated()
                                .requestMatchers("/test/mine").authenticated()
                                .requestMatchers("/password/**").permitAll()
                                .requestMatchers("/user/edit-profile").authenticated()
                                .anyRequest().permitAll()
//                        .requestMatchers("/**").permitAll()
                )

                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class).exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(restAuthenticationEntryPoint);
                    ex.accessDeniedHandler(customAccessDeniedHandler);
                });
        return http.build();

    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//
//    }
}
