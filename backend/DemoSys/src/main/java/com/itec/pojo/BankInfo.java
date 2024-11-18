package com.itec.pojo;

import org.springframework.stereotype.Component;

@Component
public class BankInfo {
	private String bankCd;
	private String bankChrcd;
	private String custBankNm;
	private String custBranchNm;
	private String custDeposittype;
	private String custBranchcd;
	private String custAccountno;
	private String custNominee;

	public String getBankCd() {
		return bankCd;
	}

	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}

	public String getBankChrcd() {
		return bankChrcd;
	}

	public void setBankChrcd(String bankChrcd) {
		this.bankChrcd = bankChrcd;
	}

	public String getCustBankNm() {
		return custBankNm;
	}

	public void setCustBankNm(String custBankNm) {
		this.custBankNm = custBankNm;
	}

	public String getCustBranchNm() {
		return custBranchNm;
	}

	public void setCustBranchNm(String custBranchNm) {
		this.custBranchNm = custBranchNm;
	}

	public String getCustDeposittype() {
		return custDeposittype;
	}

	public void setCustDeposittype(String custDeposittype) {
		this.custDeposittype = custDeposittype;
	}

	public String getCustBranchcd() {
		return custBranchcd;
	}

	public void setCustBranchcd(String custBranchcd) {
		this.custBranchcd = custBranchcd;
	}

	public String getCustAccountno() {
		return custAccountno;
	}

	public void setCustAccountno(String custAccountno) {
		this.custAccountno = custAccountno;
	}

	public String getCustNominee() {
		return custNominee;
	}

	public void setCustNominee(String custNominee) {
		this.custNominee = custNominee;
	}

	public BankInfo(String bankCd, String bankChrcd, String custBankNm, String custBranchNm, String custDeposittype,
			String custBranchcd, String custAccountno, String custNominee) {
		super();
		this.bankCd = bankCd;
		this.bankChrcd = bankChrcd;
		this.custBankNm = custBankNm;
		this.custBranchNm = custBranchNm;
		this.custDeposittype = custDeposittype;
		this.custBranchcd = custBranchcd;
		this.custAccountno = custAccountno;
		this.custNominee = custNominee;
	}

	public BankInfo() {
		super();
	}

	public BankInfo(String custBankNm, String custBranchNm, String custDeposittype, String custBranchcd,
			String custAccountno, String custNominee) {
		super();
		this.custBankNm = custBankNm;
		this.custBranchNm = custBranchNm;
		this.custDeposittype = custDeposittype;
		this.custBranchcd = custBranchcd;
		this.custAccountno = custAccountno;
		this.custNominee = custNominee;
	}

}
