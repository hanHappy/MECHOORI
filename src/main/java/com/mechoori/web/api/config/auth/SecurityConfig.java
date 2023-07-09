package com.mechoori.web.api.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 403 방지
            .csrf(csrf->csrf.disable())	
            // h2-console 화면을 사용하기 위해 해당 옵션 disable
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            //액세스 권한 설정
			.authorizeHttpRequests(
                auth->auth
                    .requestMatchers("/admin/**").hasAnyRole("ADMIN")
					.requestMatchers("/restaurant/*/rate").hasAnyRole("ADMIN", "MEMBER")
					.requestMatchers("/user/my-page/**").hasAnyRole("ADMIN", "MEMBER")
                    // .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // /api/v1/** 은 USER권한만 접근 가능
					.anyRequest().permitAll())
            .formLogin(login -> login
                .loginPage("/user/login"))
            .logout(logout -> logout
                .logoutSuccessUrl("/"))
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/")
                .successHandler((request, response, authentication) -> {
                        response.sendRedirect("/");
                    })
                .authorizationEndpoint(authorization -> authorization.baseUri("/oauth2/authorize"))
                .redirectionEndpoint(redirection -> redirection.baseUri("/login/oauth2/code/**"))
                .userInfoEndpoint(userInfo -> userInfo
                    // oauth2 로그인 성공 시의 설정값
                    // 후속 조치를 진행할 UserService 인터페이스 구현체 등록
                    // => 리소스 서버 -> 사용자 정보 가지고 추가 진행할 기능 명시
                    .userService(customOAuth2UserService)));
                  

        return http.build();
    }
}
