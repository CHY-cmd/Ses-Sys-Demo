package com.itec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itec.pojo.Operate;

@Service
public interface OperateService {

	// 添加工程师
	int insertOper(List<Operate> operate);

	// 单独更新工程师
	int updateOperateByOperNo(List<Operate> operates);
}
