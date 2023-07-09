// package com.mechoori.web.api.config.auth.dto;

// import java.io.Serializable;

// import com.mechoori.web.entity.Member;
// import com.mechoori.web.api.entity.enums.Role;

// import lombok.Getter;

// @Getter
// public class SessionUser implements Serializable {
//         //인증된 사용자 정보만 필요\
//         private long id;
//         private String username;
//         private String nickname;
//         private String email;
//         // private String img;
//         private Role role;

//     public SessionUser(Member member) {
//         this.id = member.getId();
//         this.username = member.getUsername();
//         this.nickname = member.getNickname();
//         this.email = member.getEmail();
//         // this.img = member.getImg();
//         this.role = member.getRole();
//     }
// }

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Map;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.oauth2.core.user.OAuth2User;

// import com.mechoori.web.api.entity.Member;
// import com.mechoori.web.api.entity.enums.Role;

// import lombok.Getter;

// @Getter
// public class SessionUser implements UserDetails, OAuth2User {
//     private Member member;
//     private Role role;
//     private Map<String, Object> attributes;

//     public SessionUser(Member member) {
//         this.member = member;
//         if (member != null) {
//                 this.role = member.getRole();
//     }    }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//         authorities.add(new SimpleGrantedAuthority(role.getKey()));
//         return authorities;
//     }

//     @Override
//     public String getPassword() {
//         return member.getPassword();
//     }

//     @Override
//     public String getUsername() {
//         return member.getUsername();
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

//      @Override
//     public Map<String, Object> getAttributes() {
//         return attributes;
//     }

//     @Override
//     public String getName() {
//         return member.getUsername();
//     }

//     public void setAttributes(Map<String, Object> attributes) {
//         this.attributes = attributes;
//     }
// }