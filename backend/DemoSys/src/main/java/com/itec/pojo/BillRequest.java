package com.itec.pojo;

import java.util.List;

public class BillRequest {
	private List<String> billNos;

	public List<String> getBillNos() {
		return billNos;
	}

	public void setBillNos(List<String> billNos) {
		this.billNos = billNos;
	}

	public BillRequest(List<String> billNos) {
		super();
		this.billNos = billNos;
	}

	public BillRequest() {
		super();
	}

}
