package com.mechoori.web.entity;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Member {

	@Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스
	private Integer id;
	@Transient
    @Enumerated(EnumType.STRING)
    private String role;
	@Column
	private Integer roleId;
	@Column(nullable = false)
	private String username;
	@Column
	private String nickname;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String img;
	@Column
	private Integer loginTypeId;
	@Column
	private Date regDate;

	public Member update(String username){
        this.username = username;
        return this;
    }

    // public String getRoleKey(){
    //     return this.role.getKey();
    // }
}
