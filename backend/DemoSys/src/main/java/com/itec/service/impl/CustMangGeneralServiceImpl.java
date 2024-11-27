package com.itec.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itec.mapper.CustMangGeneralMapper;
import com.itec.pojo.CustMangGeneral;
import com.itec.pojo.CustMangRequest;
import com.itec.pojo.vo.GeneralQuaryCondition;
import com.itec.pojo.vo.R;
import com.itec.service.CustMangGeneralService;

@Service
public class CustMangGeneralServiceImpl implements CustMangGeneralService {
	@Autowired
	CustMangGeneralMapper custMangGeneralMapper;

	private static final Logger logger = LoggerFactory.getLogger(CustMangGeneralService.class);

//分页查询概览及页数
	@Transactional
	@Override
	public R selectGeneral(Integer currentPage, Integer limit, GeneralQuaryCondition generalQuaryCondition) {
		Integer startIndex = (currentPage - 1) * limit;
		List<CustMangGeneral> selectGeneral = custMangGeneralMapper.selectGeneral(startIndex, limit,
				generalQuaryCondition);
		Integer selectGeneralNum = custMangGeneralMapper.selectGeneralNum(generalQuaryCondition);
		if (selectGeneralNum > 0) {
			return R.success().data("items", selectGeneral).data("itemsNum", selectGeneralNum);
		} else {
			return R.success().message("検索結果は0件です").data("itemsNum", selectGeneralNum);
		}
	}

	// 通过id查询客户、经理信息
	@Transactional
	@Override
	public R selectDetailByCustId(String custId) {
		List<CustMangRequest> selectDetailByCustId = custMangGeneralMapper.selectDetailByCustId(custId);
		logger.debug("custId: {}", custId);
		return R.success().data("items", selectDetailByCustId);
	}

}
