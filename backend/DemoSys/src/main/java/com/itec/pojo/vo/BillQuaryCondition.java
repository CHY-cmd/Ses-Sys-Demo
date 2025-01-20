package com.itec.pojo.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BillQuaryCondition {
	private String billCls;
	private String billNm;
	private String billProjectNm;
	private String billdetlEngineerNm;
	private Date billStartdt;
	private Date billEndtdt;
	private String billStatus;

	public String getBillCls() {
		return billCls;
	}

	public void setBillCls(String billCls) {
		this.billCls = billCls;
	}

	public String getBillNm() {
		return billNm;
	}

	public void setBillNm(String billNm) {
		this.billNm = billNm;
	}

	public String getBillProjectNm() {
		return billProjectNm;
	}

	public void setBillProjectNm(String billProjectNm) {
		this.billProjectNm = billProjectNm;
	}

	public String getBilldetlEngineerNm() {
		return billdetlEngineerNm;
	}

	public void setBilldetlEngineerNm(String billdetlEngineerNm) {
		this.billdetlEngineerNm = billdetlEngineerNm;
	}

	public Date getBillStartdt() {
		return billStartdt;
	}

	public void setBillStartdt(Date billStartdt) {
		this.billStartdt = billStartdt;
	}

	public Date getBillEndtdt() {
		return billEndtdt;
	}

	public void setBillEndtdt(Date billEndtdt) {
		this.billEndtdt = billEndtdt;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public BillQuaryCondition(String billCls, String billNm, String billProjectNm, String billdetlEngineerNm,
			Date billStartdt, Date billEndtdt, String billStatus) {
		super();
		this.billCls = billCls;
		this.billNm = billNm;
		this.billProjectNm = billProjectNm;
		this.billdetlEngineerNm = billdetlEngineerNm;
		this.billStartdt = billStartdt;
		this.billEndtdt = billEndtdt;
		this.billStatus = billStatus;
	}

	public BillQuaryCondition() {
		super();
	}

}
