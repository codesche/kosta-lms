package com.kosta.config;

import com.kosta.common.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    // 특정 부분에 스프링 시큐리티 기능 비활성화
    @Bean
    WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        new AntPathRequestMatcher("/static/**"),
                        new AntPathRequestMatcher("/favicon.ico") // 해당 경로 보안 해제
                );
    }

    //인증 관리자(AuthenticationManager) 설정
    @Bean
    AuthenticationManager authenticationManager(
            HttpSecurity http, BCryptPasswordEncoder bCrypt, UserDetailsService uds) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCrypt);
        return new ProviderManager(authProvider);
    }

    // 비밀번호 암호화를 위한 사용 설정
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // HTTP 요청에 따른 보안 구성
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/logout"),
                                new AntPathRequestMatcher("/static/**"),
                                new AntPathRequestMatcher("/err"),
                                new AntPathRequestMatcher("/sign-up"),
                                new AntPathRequestMatcher("/attend/**"),
                                new AntPathRequestMatcher("/course/**"),
                                new AntPathRequestMatcher("/admin/**"),
                                new AntPathRequestMatcher("/favicon.ico")
                        ).permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/admin/**")
                        ).hasRole("ADMIN")
                        .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .successHandler(loginSuccessHandler));

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true));

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        return http.getOrBuild();
    }

}
