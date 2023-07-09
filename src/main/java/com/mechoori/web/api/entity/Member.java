// package com.mechoori.web.api.entity;

// import java.util.Date;

// // import com.mechoori.web.api.entity.enums.LoginType;
// // import com.mechoori.web.api.entity.enums.LoginTypeConverter;
// import com.mechoori.web.api.entity.enums.Role;

// import jakarta.persistence.Column;
// // import jakarta.persistence.Convert;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Transient;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// public class Member {
    
//     @Id //식별자
//     @GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스
//     private long id;

//     @Transient
//     @Enumerated(EnumType.STRING)
//     private Role role;

//     @Column(nullable = false)
//     private String username;

//     // @Convert(converter = LoginTypeConverter.class)
//     // @Column(name = "login_type_id")
//     // private Integer loginTypeId;

//     @Column
//     private String nickname;
//     @Column
//     private String email;
//     @Column
//     private String password;
//     // @Column
//     // private String img;
//     // @Column
//     @Column
//     private Date regDate;

//     @Builder
//     public Member(Role role, String username, String nickname, String email, String password, Date regDate) {
//         //, String img, Integer loginTypeId
//         this.role = role;
//         this.username = username;
//         this.nickname = nickname;
//         this.email = email;
//         this.password = password;
//         // this.img = img;
//         // this.loginTypeId = loginTypeId;
//         this.regDate = regDate;
//     }

//     public Member update(String username){
//         this.username = username;
//         return this;
//     }

//     public String getRoleKey(){
//         return this.role.getKey();
//     }

//     public long getId() {
//         return this.id;
//     }

//     public void setId(long id) {
//         this.id = id;
//     }

//     public Role getRole() {
//         return this.role;
//     }

//     public void setRole(Role role) {
//         this.role = role;
//     }

//     public String getUsername() {
//         return this.username;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     public String getNickname() {
//         return this.nickname;
//     }

//     public void setNickname(String nickname) {
//         this.nickname = nickname;
//     }

//     public String getEmail() {
//         return this.email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return this.password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     // public String getImg() {
//     //     return this.img;
//     // }

//     // public void setImg(String img) {
//     //     this.img = img;
//     // }

//     // public Integer getLoginTypeId() {
//     //     return this.loginTypeId;
//     // }

//     // public void setLoginTypeId(Integer loginTypeId) {
//     //     this.loginTypeId = loginTypeId;
//     // }

//     public Date getRegDate() {
//         return this.regDate;
//     }

//     public void setRegDate(Date regDate) {
//         this.regDate = regDate;
//     }
// }
