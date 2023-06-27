package com.mechoori.web.config.security;

import com.mechoori.web.config.jwt.JwtAuthenticationFilter;
import com.mechoori.web.config.jwt.JwtTokenProvider;
import com.mechoori.web.api.service.CustomOAuth2UserService;
import com.mechoori.web.config.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.mechoori.web.config.oauth.OAuth2AuthenticationFailureHandler;
import com.mechoori.web.config.oauth.OAuth2AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, CustomOAuth2UserService customOAuth2UserService, OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler, OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/**").permitAll()
                    .requestMatchers("/api/**").permitAll()
                    .requestMatchers("/login/**").permitAll()
                    .requestMatchers("/oauth2/**").permitAll()
                    .requestMatchers("/index").permitAll() // 인덱스 페이지에 대한 접근 허용
                    .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                    .successHandler((request, response, authentication) -> {
                    // 원하는 URL로 리디렉션 처리
                    response.sendRedirect("/index");
                   })
                    .authorizationEndpoint(authorization -> authorization.baseUri("/oauth2/authorize"))
                    .redirectionEndpoint(redirection -> redirection.baseUri("/login/oauth2/code/**"))
                    .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler)
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}