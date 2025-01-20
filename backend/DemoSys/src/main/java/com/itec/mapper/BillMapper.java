package com.itec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itec.pojo.Bill;
import com.itec.pojo.BillBillDtelRequest;
import com.itec.pojo.vo.BillQuaryCondition;

@Mapper
public interface BillMapper {
	// 分页查询概览
	List<Bill> selectBillGeneral(@Param("startIndex") Integer startIndex, @Param("limit") Integer limit,
			@Param("billQuaryCondition") BillQuaryCondition billQuaryCondition);

	// 分页查询页数
	Integer selectBillGeneralNum(@Param("billQuaryCondition") BillQuaryCondition billQuaryCondition);

	// ID查询总
	List<BillBillDtelRequest> selectBillBillDeltById(@Param("billNo") String billNo);

	// 查询所有账单
	@Select("select * from BILL_TBL where BILL_DELFLG = 0")
	List<Bill> selectAllBill();

	// 逻辑删除
	int deleteBillById(@Param("billNo") String billNo);

	// 单独新增
	int insertBill(Bill bill);

	// 单独更新
	int updateBillById(Bill bill);
}