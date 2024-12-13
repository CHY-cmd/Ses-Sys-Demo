package com.itec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itec.mapper.ContractMapper;
import com.itec.pojo.ContrOperRequest;
import com.itec.pojo.Contract;
import com.itec.pojo.vo.ContractQuaryCondition;
import com.itec.pojo.vo.R;
import com.itec.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	ContractMapper contractMapper;

	@Override
	public R selectContrGeneral(Integer currentPage, Integer limit, ContractQuaryCondition contractQuaryCondition) {
		Integer startIndex = (currentPage - 1) * limit;
		List<Contract> selectContrGeneral = contractMapper.selectContrGeneral(startIndex, limit,
				contractQuaryCondition);
		Integer selectContrGeneralNum = contractMapper.selectContrGeneralNum(contractQuaryCondition);
		if (selectContrGeneralNum > 0) {
			return R.success().data("items", selectContrGeneral).data("itemsNum", selectContrGeneralNum);
		} else {
			return R.success().message("検索結果は0件です").data("itemsNum", selectContrGeneral);
		}
	}

	@Override
	public R selectContrOperById(String contrNo) {
		List<ContrOperRequest> selectContrOperById = contractMapper.selectContrOperById(contrNo);
		return R.success().data("items", selectContrOperById);
	}

	@Override
	public R deleteContrById(String contrNo) {
		int i = contractMapper.deleteContrById(contrNo);
		if (i > 0) {
			return R.success().message("削除が完了しました");
		} else {
			return R.success().message("削除に失敗しました");
		}
	}

}
