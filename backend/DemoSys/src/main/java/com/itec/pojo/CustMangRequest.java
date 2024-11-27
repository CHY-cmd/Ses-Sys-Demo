package com.itec.pojo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CustMangRequest {
	private CustInfo custInfo;
	private List<MangInfo> mangInfo;

	public CustInfo getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfo custInfo) {
		this.custInfo = custInfo;
	}

	public List<MangInfo> getMangInfo() {
		return mangInfo;
	}

	public void setMangInfo(List<MangInfo> mangInfo) {
		this.mangInfo = mangInfo;
	}

	public CustMangRequest(CustInfo custInfo, List<MangInfo> mangInfo) {
		super();
		this.custInfo = custInfo;
		this.mangInfo = mangInfo;
	}

	public CustMangRequest() {
		super();
	}

	@Override
	public String toString() {
		return "CustMangAdd [custInfo=" + custInfo + ", mangInfo=" + mangInfo + "]";
	}

}
