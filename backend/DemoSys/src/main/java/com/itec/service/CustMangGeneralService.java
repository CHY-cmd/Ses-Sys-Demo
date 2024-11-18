package com.itec.service;

import org.springframework.stereotype.Service;

import com.itec.pojo.vo.GeneralQuaryCondition;
import com.itec.pojo.vo.R;

@Service
public interface CustMangGeneralService {
	// 分页查询概览
	R selectGeneral(Integer currentPage, Integer limit, GeneralQuaryCondition generalQuaryCondition);

	// 通过id合并查询
	R selectDetailByCustId(String custId);

}
