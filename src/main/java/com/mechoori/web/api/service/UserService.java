// package com.mechoori.web.api.service;

// import java.util.Optional;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.mechoori.web.api.entity.Member;
// import com.mechoori.web.api.repository.MemberApiRepository;
// import com.mechoori.web.config.jwt.JwtTokenProvider;

// import jakarta.transaction.Transactional;

// @Service
// @Transactional
// public class UserService {

//     private final BCryptPasswordEncoder encoder;
//     private final MemberApiRepository repository;
//     private final AuthenticationManagerBuilder authenticationManagerBuilder;
//     private final JwtTokenProvider jwtTokenProvider;
//     private UserDetailsService userDetailsService;

//     public UserService(BCryptPasswordEncoder encoder, MemberApiRepository repository, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
//         this.encoder = encoder;
//         this.repository = repository;
//         this.authenticationManagerBuilder = authenticationManagerBuilder;
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     public void configure(AuthenticationManagerBuilder auth) throws Exception {
//     auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//     }

//     public String login(String email, String password) {
//         UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

//         // 검증
//         Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

//         // 검증된 인증 정보로 JWT 토큰 생성
//         String token = jwtTokenProvider.generateToken(authentication);

//         return token;
//     }
    
//     public Member getByEmail(String email) {
//         Optional<Member> userOptional = repository.findByEmail(email);
//         return userOptional.orElse(null);
//     }
// }
//     // public Long signup(SignupForm signupForm) {
//     //     boolean check = checkEmailExists(signupForm.getEmail());

//     //     if (check) {
//     //         throw new IllegalArgumentException("이미 존재하는 유저입니다.");
//     //     }

//     //     String encPwd = encoder.encode(signupForm.getPassword());

//     //     Users user = repository.save(signupForm.toEntity(encPwd));

//     //     if(user!=null) {
//     //         return user.getId();
//     //     }
//     //     throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//     // }

//     // public boolean checkEmailExists(String email) {
//     //    return repository.existsUsersByEmail(email);
//     // }

