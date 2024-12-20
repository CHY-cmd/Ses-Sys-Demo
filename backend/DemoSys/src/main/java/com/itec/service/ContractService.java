package com.itec.service;

import org.springframework.stereotype.Service;

import com.itec.pojo.Contract;
import com.itec.pojo.vo.ContractQuaryCondition;
import com.itec.pojo.vo.R;

@Service
public interface ContractService {
	// 分页查询概览
	R selectContrGeneral(Integer currentPage, Integer limit, ContractQuaryCondition contractQuaryCondition);

	// ID查询contr及oper
	R selectContrOperById(String contrNo);

	// 逻辑删除
	R deleteContrById(String contrNo);

	// 单独更新合同
	int updateContrById(Contract contract);

	// 单独添加合同
	int insertContr(Contract contract);

	R selectTest(String contrNo);
}
