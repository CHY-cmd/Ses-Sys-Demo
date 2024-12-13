package com.itec.pojo;

import java.util.List;

public class ContrOperRequest {

	private Contract contract;
	private List<Operate> operates;

	public List<Operate> getOperates() {
		return operates;
	}

	public void setOperates(List<Operate> operates) {
		this.operates = operates;
	}

	public ContrOperRequest(Contract contract, List<Operate> operates) {
		super();
		this.setContract(contract);
		this.operates = operates;
	}

	public ContrOperRequest() {
		super();
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
