package com.mechoori.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	   private Integer id;
	   private Integer roleId;
       private Integer genderId;
	   private String username;
       private String nickname;
	   private String password;
	   private String email;
	   private String phoneNumber;
	   private Date regDate;
	   private Date birthDate;
	}
