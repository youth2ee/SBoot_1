package com.naver.b1.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberVO {
	
	@NotEmpty(message = "ID를 반드시 입력해주세요.")
	private String id;
	
	@NotEmpty
	@Pattern(regexp = "[0-9]+")
	@Size(min = 4, max = 10, message = "PassWord는 4자리 이상 10자리 이하로 입력해주세요.")
	private String pw;
	
	@Size(min = 4, max = 10) //max : int
	private String pw2;
	
	private String name;
	
	@Email //이메일 형식에 맞지 않으면 action을 취하겠다.
	private String email;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPw2() {
		return pw2;
	}
	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
