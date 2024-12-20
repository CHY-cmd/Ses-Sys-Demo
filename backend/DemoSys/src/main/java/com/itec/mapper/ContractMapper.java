package com.itec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itec.pojo.ContrOperRequest;
import com.itec.pojo.Contract;
import com.itec.pojo.vo.ContractQuaryCondition;

@Mapper
public interface ContractMapper {
	// 分页查询概览
	List<Contract> selectContrGeneral(@Param("startIndex") Integer startIndex, @Param("limit") Integer limit,
			@Param("contractQuaryCondition") ContractQuaryCondition contractQuaryCondition);

	// 分页查询页数
	Integer selectContrGeneralNum(@Param("contractQuaryCondition") ContractQuaryCondition contractQuaryCondition);

	// ID查询contr及oper
	List<ContrOperRequest> selectContrOperById(@Param("contrNo") String contrNo);

	// 逻辑删除
	int deleteContrById(@Param("contrNo") String contrNo);

	// 单独更新合同
	int updateContrById(Contract contract);

	// 单独添加合同
	int insertContr(Contract contract);

	// 测试
	List<Contract> selectTest(@Param("contrNo") String contrNo);
}