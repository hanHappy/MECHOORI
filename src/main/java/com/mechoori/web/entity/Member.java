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
	   private String username;
	   private String email;
	   private String password;
	   private String img;
	   private Integer loginTypeId;
	   private Date regDate;
}
