package com.itec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.itec.pojo.BillDetl;

@Mapper
public interface BillDetlMapper {
	// 添加
	int insertBillDetl(List<BillDetl> billDetls);

	// 单独更新
	int updateBillDetlByBillDetlNo(List<BillDetl> billDetls);

}