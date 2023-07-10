package com.mechoori.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.mechoori.web.api.service.MechooriOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MechooriOAuth2UserService mechooriOAuth2UserService;

    public SecurityConfig(MechooriOAuth2UserService mechooriOAuth2UserService) {
        this.mechooriOAuth2UserService = mechooriOAuth2UserService;
    }

    // 권한을 위한 필터 객체
	// HttpSecurity가 필터를 만들 수 있도록 하는 class
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // cross origin에 대해 403을 띄우는 것 방지(안전 모드를 해제하는 느낌)
            .csrf(csrf->csrf.disable())	
            // h2-console 옵션 disable
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            //액세스 권한 설정
			.authorizeHttpRequests(
                auth->auth
                    .requestMatchers("/admin/**").hasAnyRole("ADMIN")
					.requestMatchers("/restaurant/*/rate").hasAnyRole("ADMIN", "USER")
					.requestMatchers("/user/my-page/**").hasAnyRole("ADMIN", "MEMBER")
					//.requestMatchers("/user/login/**").hasAnyRole("ADMIN", "MEMBER")
					.anyRequest().permitAll()) // 이외의 요청은 전부 승인함
            .formLogin(
                login -> login
                    .loginPage("/user/login") // GET 요청
                    .loginProcessingUrl("/user/login") // POST 요청
                    .usernameParameter("email")
                    .defaultSuccessUrl("/")) // 다른 페이지에서 온 게 아니라 바로 로그인 버튼 눌렀을 때
            .logout(
                logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/"))
            .oauth2Login(
                oauth2 -> oauth2
                    .defaultSuccessUrl("/") //로그인 성공 시
            //엔드포인트
            .authorizationEndpoint(authorization -> authorization.baseUri("/oauth2/authorize")) //인가 요청 시
            .redirectionEndpoint(redirection -> redirection.baseUri("/login/oauth2/code/**")) //인증 성공 후
            .userInfoEndpoint( //사용자 정보
                userInfo -> userInfo
                    .userService(mechooriOAuth2UserService)));
                  
        return http.build();
    }

    @Bean
	public PasswordEncoder passwordEncoder() {
		// 콩자루에 담는 작업
		return new BCryptPasswordEncoder();
	}
}
