package com.mechoori.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	   private Integer id;
	   private Integer roleId;
	   private String img;
	   private String username;
	   private String name;
       private String nickname;
	   private String email;
	   private String password;
	   private String phoneNumber;
	   private Date regDate;

		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date birthDate;

}
