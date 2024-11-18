package com.itec.pojo.vo;

import org.springframework.stereotype.Component;

@Component
public class GeneralQuaryCondition {

	private String custNm;
	private String managerNm;
	private Boolean custInforSecurity;
	private Boolean custDispatch;

	public String getCustNm() {
		return custNm;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public String getManagerNm() {
		return managerNm;
	}

	public void setManagerNm(String managerNm) {
		this.managerNm = managerNm;
	}

	public Boolean getCustInforSecurity() {
		return custInforSecurity;
	}

	public void setCustInforSecurity(Boolean custInforSecurity) {
		this.custInforSecurity = custInforSecurity;
	}

	public Boolean getCustDispatch() {
		return custDispatch;
	}

	public void setCustDispatch(Boolean custDispatch) {
		this.custDispatch = custDispatch;
	}

	public GeneralQuaryCondition(String custNm, String managerNm, Boolean custInforSecurity, Boolean custDispatch) {
		super();
		this.custNm = custNm;
		this.managerNm = managerNm;
		this.custInforSecurity = custInforSecurity;
		this.custDispatch = custDispatch;
	}

	public GeneralQuaryCondition() {
		super();
	}

}
