package com.itec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itec.mapper.OperateMapper;
import com.itec.pojo.Operate;
import com.itec.service.OperateService;

@Service
public class OperateServiceImpl implements OperateService {
	@Autowired
	OperateMapper operateMapper;

	@Override
	public int insertOper(List<Operate> operate) {
		return operateMapper.insertOper(operate);
	}

	@Override
	public int updateOperateByOperNo(List<Operate> operates) {
		return operateMapper.updateOperateByOperNo(operates);
	}

}
