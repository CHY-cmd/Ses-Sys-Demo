package com.itec.pojo.vo;

import org.springframework.stereotype.Component;

@Component
public class UserQuaryCondition {
	private String userNm;
	private Byte userRole;
	private String userMailaddress;

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public Byte getUserRole() {
		return userRole;
	}

	public void setUserRole(Byte userRole) {
		this.userRole = userRole;
	}

	public String getUserMailaddress() {
		return userMailaddress;
	}

	public void setUserMailaddress(String userMailaddress) {
		this.userMailaddress = userMailaddress;
	}

	public UserQuaryCondition(String userNm, Byte userRole, String userMailaddress) {
		super();
		this.userNm = userNm;
		this.userRole = userRole;
		this.userMailaddress = userMailaddress;
	}

	public UserQuaryCondition() {
		super();
	}

}
