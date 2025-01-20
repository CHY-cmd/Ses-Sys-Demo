package com.itec.pojo;

import java.util.Date;
import java.util.List;

public class BillBillDtelRequest {
	private Bill bill;
	private List<BillDetl> billDetls;

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public List<BillDetl> getBillDetls() {
		return billDetls;
	}

	public void setBillDetls(List<BillDetl> billDetls) {
		this.billDetls = billDetls;
	}

	public BillBillDtelRequest(Bill bill, List<BillDetl> billDetls) {
		super();
		this.bill = bill;
		this.billDetls = billDetls;
	}

	public BillBillDtelRequest() {
		super();
	}

	// 添加便捷方法来获取账单结束日期和客户名称
	public Date getBillEndtdt() {
		return bill != null ? bill.getBillEndtdt() : null;
	}

	public String getBillCustNm() {
		return bill != null ? bill.getBillCustNm() : null;
	}

}
