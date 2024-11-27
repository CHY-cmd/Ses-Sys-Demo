package com.itec.pojo.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class LoginUser {
	private String userPwd;
	private String userMailaddress;
	private Date userExPwDay;
	private String userNm;

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserMailaddress() {
		return userMailaddress;
	}

	public void setUserMailaddress(String userMailaddress) {
		this.userMailaddress = userMailaddress;
	}

	public LoginUser(String userPwd, String userMailaddress) {
		super();
		this.userPwd = userPwd;
		this.userMailaddress = userMailaddress;
	}

	public LoginUser() {
		super();
	}

	public Date getUserExPwDay() {
		return userExPwDay;
	}

	public void setUserExPwDay(Date userExPwDay) {
		this.userExPwDay = userExPwDay;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

}
