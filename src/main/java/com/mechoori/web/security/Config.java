package com.mechoori.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

	// 권한을 위한 필터 객체
	// HttpSecurity가 필터를 만들 수 있도록 하는 class
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests( // 인증을 위한 요청 URL 설정
				auth->auth
					.requestMatchers("/admin/**").hasAnyRole("ADMIN")
					.requestMatchers("/restaurant/*/rate").hasAnyRole("ADMIN", "MEMBER")
					.requestMatchers("/user/my-page/**").hasAnyRole("ADMIN", "MEMBER")
					//.requestMatchers("/user/login/**").hasAnyRole("ADMIN", "MEMBER")
					.anyRequest().permitAll()) // 이외의 요청은 전부 승인함
			.formLogin(form -> form
				.loginPage("/user/login")
				.loginProcessingUrl("/user/login")
				.usernameParameter("email")
				.defaultSuccessUrl("/index"))
			.logout(logout -> logout.logoutUrl("/sign-in"));

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// 콩자루에 담는 작업
		return new BCryptPasswordEncoder();
	}
}