package com.itec.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.itec.pojo.vo.ContractQuaryCondition;
import com.itec.pojo.vo.R;

@Service
public interface ContractService {
	// 分页查询概览
	R selectContrGeneral(Integer currentPage, Integer limit, ContractQuaryCondition contractQuaryCondition);

	// ID查询contr及oper
	R selectContrOperById(String contrNo);

	// 逻辑删除
	R deleteContrById(@Param("contrNo") String contrNo);
}
