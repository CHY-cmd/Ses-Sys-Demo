package com.itec.pojo.vo;

import org.springframework.stereotype.Component;

@Component
public class StaffQuaryCondition {
	private String staffNm;
	private String staffCls;
	private String staffSalesstatus;

	public String getStaffNm() {
		return staffNm;
	}

	public void setStaffNm(String staffNm) {
		this.staffNm = staffNm;
	}

	public String getStaffCls() {
		return staffCls;
	}

	public void setStaffCls(String staffCls) {
		this.staffCls = staffCls;
	}

	public String getStaffSalesstatus() {
		return staffSalesstatus;
	}

	public void setStaffSalesstatus(String staffSalesstatus) {
		this.staffSalesstatus = staffSalesstatus;
	}

	public StaffQuaryCondition(String staffNm, String staffCls, String staffSalesstatus) {
		super();
		this.staffNm = staffNm;
		this.staffCls = staffCls;
		this.staffSalesstatus = staffSalesstatus;
	}

	public StaffQuaryCondition() {
		super();
	}

}
