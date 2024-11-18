package com.itec.pojo;

import org.springframework.stereotype.Component;

@Component
public class MangInfoKey {
	private String managerCd;
	private String managerChrcd;

	public String getManagerCd() {
		return managerCd;
	}

	public void setManagerCd(String managerCd) {
		this.managerCd = managerCd;
	}

	public String getManagerChrcd() {
		return managerChrcd;
	}

	public void setManagerChrcd(String managerChrcd) {
		this.managerChrcd = managerChrcd;
	}

	public MangInfoKey(String managerCd, String managerChrcd) {
		super();
		this.managerCd = managerCd;
		this.managerChrcd = managerChrcd;
	}

	public MangInfoKey() {
		super();
	}

}
