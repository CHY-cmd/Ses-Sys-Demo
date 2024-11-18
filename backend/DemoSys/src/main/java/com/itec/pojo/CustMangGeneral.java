package com.itec.pojo;

import org.springframework.stereotype.Component;

@Component
public class CustMangGeneral {
	private String custId;
	private String custNm;
	private String custPhone;
	private boolean custDelflg;
	private String custAddress;
	private String managerNm;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustNm() {
		return custNm;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public boolean getCustDelflg() {
		return custDelflg;
	}

	public void setCustDelflg(boolean custDelflg) {
		this.custDelflg = custDelflg;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getManagerNm() {
		return managerNm;
	}

	public void setManagerNm(String managerNm) {
		this.managerNm = managerNm;
	}

	public CustMangGeneral(String custId, String custNm, String custPhone, boolean custDelflg, String custAddress,
			String managerNm) {
		super();
		this.custId = custId;
		this.custNm = custNm;
		this.custPhone = custPhone;
		this.custDelflg = custDelflg;
		this.custAddress = custAddress;
		this.managerNm = managerNm;
	}

	public CustMangGeneral() {
		super();
	}

}
