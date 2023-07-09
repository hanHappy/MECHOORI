package com.mechoori.web.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.mechoori.web.entity.Member;

public class MechooriUserDetails implements UserDetails, OAuth2User {

    private Member member;
    private Map<String,Object> attributes;
    private List<GrantedAuthority> authorities;

    //일반
    public MechooriUserDetails(Member member) {
        this.member = member;
    }

    //소셜
    public MechooriUserDetails(Member member, Map<String,Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    public int getId() {
        return member.getId();
    }

    public String getNickname() {
        return member.getNickname();
    }

    public String getEmail() {
        return member.getEmail();
    }

    public String getImg() {
        return member.getImg();
    }

    public Date getRegDate() {
        return member.getRegDate();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}




// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Date;
// import java.util.List;
// import java.util.Map;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.oauth2.core.user.OAuth2User;

// public class MechooriUserDetails implements UserDetails, OAuth2User {
//     private int id;
//     private String username;
//     private String nickname;
//     private String email;
//     private String password;
//     private String img;
//     private Date regDate;
//     private List<GrantedAuthority> authorities;

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return authorities;
//     }
    
//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     @Override
//     public String getUsername() {
//         return username;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     public String getNickname() {
//         return nickname;
//     }

//     public void setNickname(String nickname) {
//         this.nickname = nickname;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public String getImg() {
//         return img;
//     }

//     public void setImg(String img) {
//         this.img = img;
//     }

//     public Date getRegDate() {
//         return regDate;
//     }

//     public void setRegDate(Date regDate) {
//         this.regDate = regDate;
//     }

//     public void setAuthorities(List<GrantedAuthority> authorities) {
//         this.authorities = authorities;
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
// }
