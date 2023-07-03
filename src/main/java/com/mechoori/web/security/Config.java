package com.mechoori.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mechoori.web.api.service.CustomOAuth2UserService;
import com.mechoori.web.config.jwt.JwtAuthenticationFilter;
import com.mechoori.web.config.jwt.JwtTokenProvider;
import com.mechoori.web.config.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.mechoori.web.config.oauth.OAuth2AuthenticationFailureHandler;
import com.mechoori.web.config.oauth.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class Config {

	private final JwtTokenProvider jwtTokenProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    public Config(JwtTokenProvider jwtTokenProvider, CustomOAuth2UserService customOAuth2UserService, OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler, OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
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

	// 권한을 위한 필터 객체
	// HttpSecurity가 필터를 만들 수 있도록 하는 class
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf->csrf.disable())	// cross origin에 대해 403을 띄우는 것 방지(안전 모드를 해제하는 느낌)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests( // 인증을 위한 요청 URL 설정
				auth->auth
					.requestMatchers("/admin/**").hasAnyRole("ADMIN")
					.requestMatchers("/restaurant/*/rate").hasAnyRole("ADMIN", "MEMBER")
					.requestMatchers("/user/my-page/**").hasAnyRole("ADMIN", "MEMBER")
					//.requestMatchers("/user/login/**").hasAnyRole("ADMIN", "MEMBER")
					.anyRequest().permitAll()) // 이외의 요청은 전부 승인함
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
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.formLogin(
				form->form
					.loginPage("/user/login") // GET 요청
					.loginProcessingUrl("/user/login") // POST 요청
					.usernameParameter("email")
					.defaultSuccessUrl("/index")) // 다른 페이지에서 온 게 아니라 바로 로그인 버튼 눌렀을 때
			.logout(
				logout->logout
				.logoutUrl("/sign-in") // logout 시 페이지
			); 
		
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// 콩자루에 담는 작업
		return new BCryptPasswordEncoder();
	}
}