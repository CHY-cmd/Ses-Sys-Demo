package com.itec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itec.pojo.CustMangGeneral;
import com.itec.pojo.CustMangRequest;
import com.itec.pojo.vo.GeneralQuaryCondition;

@Mapper
public interface CustMangGeneralMapper {
	// 分页查询概览
	List<CustMangGeneral> selectGeneral(@Param("startIndex") Integer startIndex, @Param("limit") Integer limit,
			@Param("generalQuaryCondition") GeneralQuaryCondition generalQuaryCondition);

	// 分页查询页数
	Integer selectGeneralNum(@Param("generalQuaryCondition") GeneralQuaryCondition generalQuaryCondition);

	// 通过id合并查询
	List<CustMangRequest> selectDetailByCustId(String custId);

}
