package com.itec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itec.pojo.MangInfo;

@Service
public interface MangInfoService {
	// 添加经理信息
	int insertMang(List<MangInfo> mangInfo);

	// 更新经理表
	int updateMangByCustId(List<MangInfo> mangInfos);

}
