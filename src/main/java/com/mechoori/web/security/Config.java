package com.mechoori.web.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {
    @Autowired
	private DataSource datasource;

	// 권한을 위한 필터 객체
	// HttpSecurity가 필터를 만들 수 있도록 하는 class
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf->csrf.disable())	// cross origin에 대해 403을 띄우는 것 방지(안전 모드를 해제하는 느낌)
			.authorizeHttpRequests( // 인증을 위한 요청 URL 설정
				auth->auth
					.requestMatchers("/admin/**").hasAnyRole("ADMIN") // authority 칼럼이 ROLE_ADMIN로 되어 있는 경우
					.anyRequest().permitAll()) // 이외의 요청은 전부 승인함
			.formLogin(
				form->form
					.loginPage("/user/login") // GET 요청
					.loginProcessingUrl("/user/login") // POST 요청
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

	@Bean
	public UserDetailsService jdbcUserDetailsService() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(datasource);

		manager.setUsersByUsernameQuery("select username, password, 1 enabled from member where username = ?"); // username,
		manager.setAuthoritiesByUsernameQuery(
				"select m.username, r.name authority from role r join member m on m.role_id = r.id where m.username=?"); // username,

		return manager;
	}
}