package com.kosta.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    WebSecurityCustomizer configure() {
        return (web -> web.ignoring()
                .requestMatchers(
                        new AntPathRequestMatcher("/static/**")
                ));
    }

    // HTTP 요청에 따른 보안 구성
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/admin/**")
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/err"),

                                // 인증, 인가 설정 (특정한 URL 액세스를 설정)
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/attend/list"),
                                new AntPathRequestMatcher("/favicon.ico")       // 해당 경로 추가 후 No static resource 에러 해결
                        ).permitAll()
                        .anyRequest().authenticated()
        );

        http.formLogin(form -> form.loginPage("/index").defaultSuccessUrl("/"));
        http.logout(logout -> logout.logoutSuccessUrl("/").invalidateHttpSession(true));
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        return http.getOrBuild();
    }

}
