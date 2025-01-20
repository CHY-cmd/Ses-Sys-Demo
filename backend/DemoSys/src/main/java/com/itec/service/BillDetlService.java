package com.itec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itec.pojo.BillDetl;

@Service
public interface BillDetlService {
	// 添加
	int insertBillDetl(List<BillDetl> billDetls);

	// 单独更新
	int updateBillDetlByBillDetlNo(List<BillDetl> billDetls);
}
