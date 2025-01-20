package com.itec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itec.mapper.BillDetlMapper;
import com.itec.pojo.BillDetl;
import com.itec.service.BillDetlService;

@Service
public class BillDetlServiceImpl implements BillDetlService {

	@Autowired
	BillDetlMapper billDetlMapper;

	@Override
	public int insertBillDetl(List<BillDetl> billDetls) {
		return billDetlMapper.insertBillDetl(billDetls);
	}

	@Override
	public int updateBillDetlByBillDetlNo(List<BillDetl> billDetls) {
		return billDetlMapper.updateBillDetlByBillDetlNo(billDetls);
	}

}
