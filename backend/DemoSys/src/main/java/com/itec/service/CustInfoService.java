package com.itec.service;

import org.springframework.stereotype.Service;

import com.itec.pojo.CustInfo;
import com.itec.pojo.vo.R;

@Service
public interface CustInfoService {
//查询详细客户信息
	R selectAllCustInfos();

	// 通过id删除客户信息
	R deleteCustByID(String custId);

	// 添加客户信息
	int insertCust(CustInfo custInfo);

	// 查询银行信息
	R selectBank();

	// 查客户银行
	R selectCustBank();

	// 更新客户表
	R updateCustByCustId(CustInfo custInfo);

	R selectCustByNm(String custNm);
}
