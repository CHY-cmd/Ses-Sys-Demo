package com.itec.pojo.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ContractQuaryCondition {
	private String custNm;
	private String contrEngineerNm;
	private String contrProjectNm;
	private String quotDelflg;
	private Date contrStartdt;
	private Date contrEnddt;
	private String contrStatus;

	public ContractQuaryCondition(String custNm, String contrEngineerNm, String contrProjectNm, String quotDelflg,
			Date contrStartdt, Date contrEnddt, String contrStatus) {
		super();
		this.custNm = custNm;
		this.contrEngineerNm = contrEngineerNm;
		this.contrProjectNm = contrProjectNm;
		this.quotDelflg = quotDelflg;
		this.contrStartdt = contrStartdt;
		this.contrEnddt = contrEnddt;
		this.contrStatus = contrStatus;
	}

	public ContractQuaryCondition() {
		super();
	}

	public String getCustNm() {
		return custNm;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public String getContrEngineerNm() {
		return contrEngineerNm;
	}

	public void setContrEngineerNm(String contrEngineerNm) {
		this.contrEngineerNm = contrEngineerNm;
	}

	public String getContrProjectNm() {
		return contrProjectNm;
	}

	public void setContrProjectNm(String contrProjectNm) {
		this.contrProjectNm = contrProjectNm;
	}

	public String getQuotDelflg() {
		return quotDelflg;
	}

	public void setQuotDelflg(String quotDelflg) {
		this.quotDelflg = quotDelflg;
	}

	public Date getContrStartdt() {
		return contrStartdt;
	}

	public void setContrStartdt(Date contrStartdt) {
		this.contrStartdt = contrStartdt;
	}

	public Date getContrEnddt() {
		return contrEnddt;
	}

	public void setContrEnddt(Date contrEnddt) {
		this.contrEnddt = contrEnddt;
	}

	public String getContrStatus() {
		return contrStatus;
	}

	public void setContrStatus(String contrStatus) {
		this.contrStatus = contrStatus;
	}

}
