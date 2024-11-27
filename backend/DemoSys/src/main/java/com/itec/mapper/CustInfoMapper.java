package com.itec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.itec.pojo.BankInfo;
import com.itec.pojo.CustInfo;

@Mapper
public interface CustInfoMapper {
	// 查询所有
	@Select("select * from cust_tbl where CUST_DELFLG = 0")
	List<CustInfo> selectAllCustInfos();

	// 逻辑删除
	@Delete("update cust_tbl set CUST_DELFLG = 1 where CUST_DELFLG = 0 and CUST_ID = #{custId}")
	int deleteCustByID(String custId);

	// 添加客户
	int insertCust(CustInfo custInfo);

	// 查询银行信息
	@Select("select BANK_CD,BANK_CHRCD,CUST_BANK_NM,CUST_BRANCH_NM,CUST_DEPOSITTYPE,CUST_BRANCHCD,CUST_ACCOUNTNO,CUST_NOMINEE from cust_tbl where CUST_DELFLG = 0")
	List<BankInfo> selectBank();

	// 查客户银行
	@Select("select CUST_BANK_NM,CUST_BRANCH_NM,CUST_BRANCHCD,CUST_DEPOSITTYPE,CUST_ACCOUNTNO,CUST_NOMINEE from cust_tbl where CUST_DELFLG = 0 and CUST_TYPE='顧客'")
	List<BankInfo> selectCustBank();

	// 更新客户表
	int updateCustByCustId(CustInfo custInfo);

}