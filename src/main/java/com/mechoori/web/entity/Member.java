package com.mechoori.web.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Entity
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

	public Member(Integer id, String role, Integer roleId, String username, String nickname, String email, String password, String img, Integer loginTypeId, Date regDate) {
		this.id = id;
		this.role = role;
		this.roleId = roleId;
		this.username = username;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.img = img;
		this.loginTypeId = loginTypeId;
		this.regDate = regDate;
	}

	public Member() {
	}

	public static MemberBuilder builder() {
		return new MemberBuilder();
	}

	public Member update(String username) {
		this.username = username;
		return this;
	}

	public Integer getId() {
		return this.id;
	}

	public String getRole() {
		return this.role;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public String getUsername() {
		return this.username;
	}

	public String getNickname() {
		return this.nickname;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public String getImg() {
		return this.img;
	}

	public Integer getLoginTypeId() {
		return this.loginTypeId;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setLoginTypeId(Integer loginTypeId) {
		this.loginTypeId = loginTypeId;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof Member)) return false;
		final Member other = (Member) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$id = this.getId();
		final Object other$id = other.getId();
		if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
		final Object this$role = this.getRole();
		final Object other$role = other.getRole();
		if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
		final Object this$roleId = this.getRoleId();
		final Object other$roleId = other.getRoleId();
		if (this$roleId == null ? other$roleId != null : !this$roleId.equals(other$roleId)) return false;
		final Object this$username = this.getUsername();
		final Object other$username = other.getUsername();
		if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
		final Object this$nickname = this.getNickname();
		final Object other$nickname = other.getNickname();
		if (this$nickname == null ? other$nickname != null : !this$nickname.equals(other$nickname)) return false;
		final Object this$email = this.getEmail();
		final Object other$email = other.getEmail();
		if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
		final Object this$password = this.getPassword();
		final Object other$password = other.getPassword();
		if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
		final Object this$img = this.getImg();
		final Object other$img = other.getImg();
		if (this$img == null ? other$img != null : !this$img.equals(other$img)) return false;
		final Object this$loginTypeId = this.getLoginTypeId();
		final Object other$loginTypeId = other.getLoginTypeId();
		if (this$loginTypeId == null ? other$loginTypeId != null : !this$loginTypeId.equals(other$loginTypeId))
			return false;
		final Object this$regDate = this.getRegDate();
		final Object other$regDate = other.getRegDate();
		if (this$regDate == null ? other$regDate != null : !this$regDate.equals(other$regDate)) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof Member;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $id = this.getId();
		result = result * PRIME + ($id == null ? 43 : $id.hashCode());
		final Object $role = this.getRole();
		result = result * PRIME + ($role == null ? 43 : $role.hashCode());
		final Object $roleId = this.getRoleId();
		result = result * PRIME + ($roleId == null ? 43 : $roleId.hashCode());
		final Object $username = this.getUsername();
		result = result * PRIME + ($username == null ? 43 : $username.hashCode());
		final Object $nickname = this.getNickname();
		result = result * PRIME + ($nickname == null ? 43 : $nickname.hashCode());
		final Object $email = this.getEmail();
		result = result * PRIME + ($email == null ? 43 : $email.hashCode());
		final Object $password = this.getPassword();
		result = result * PRIME + ($password == null ? 43 : $password.hashCode());
		final Object $img = this.getImg();
		result = result * PRIME + ($img == null ? 43 : $img.hashCode());
		final Object $loginTypeId = this.getLoginTypeId();
		result = result * PRIME + ($loginTypeId == null ? 43 : $loginTypeId.hashCode());
		final Object $regDate = this.getRegDate();
		result = result * PRIME + ($regDate == null ? 43 : $regDate.hashCode());
		return result;
	}

	public String toString() {
		return "Member(id=" + this.getId() + ", role=" + this.getRole() + ", roleId=" + this.getRoleId() + ", username=" + this.getUsername() + ", nickname=" + this.getNickname() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", img=" + this.getImg() + ", loginTypeId=" + this.getLoginTypeId() + ", regDate=" + this.getRegDate() + ")";
	}

	public static class MemberBuilder {
		private Integer id;
		private String role;
		private Integer roleId;
		private String username;
		private String nickname;
		private String email;
		private String password;
		private String img;
		private Integer loginTypeId;
		private Date regDate;

		MemberBuilder() {
		}

		public MemberBuilder id(Integer id) {
			this.id = id;
			return this;
		}

		public MemberBuilder role(String role) {
			this.role = role;
			return this;
		}

		public MemberBuilder roleId(Integer roleId) {
			this.roleId = roleId;
			return this;
		}

		public MemberBuilder username(String username) {
			this.username = username;
			return this;
		}

		public MemberBuilder nickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public MemberBuilder email(String email) {
			this.email = email;
			return this;
		}

		public MemberBuilder password(String password) {
			this.password = password;
			return this;
		}

		public MemberBuilder img(String img) {
			this.img = img;
			return this;
		}

		public MemberBuilder loginTypeId(Integer loginTypeId) {
			this.loginTypeId = loginTypeId;
			return this;
		}

		public MemberBuilder regDate(Date regDate) {
			this.regDate = regDate;
			return this;
		}

		public Member build() {
			return new Member(this.id, this.role, this.roleId, this.username, this.nickname, this.email, this.password, this.img, this.loginTypeId, this.regDate);
		}

		public String toString() {
			return "Member.MemberBuilder(id=" + this.id + ", role=" + this.role + ", roleId=" + this.roleId + ", username=" + this.username + ", nickname=" + this.nickname + ", email=" + this.email + ", password=" + this.password + ", img=" + this.img + ", loginTypeId=" + this.loginTypeId + ", regDate=" + this.regDate + ")";
		}
	}

	// public String getRoleKey(){
	//     return this.role.getKey();
	// }
}
