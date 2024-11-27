package com.itec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itec.mapper.MangInfoMapper;
import com.itec.pojo.MangInfo;
import com.itec.service.MangInfoService;

@Service
public class MangInfoServiceImpl implements MangInfoService {

	@Autowired
	MangInfoMapper mangInfoMapper;

	// 添加经理信息
	@Override
	public int insertMang(List<MangInfo> mangInfo) {
		return mangInfoMapper.insertMang(mangInfo);
	}

	// 单独更新经理信息
	@Override
	public int updateMangByCustId(List<MangInfo> mangInfos) {
		return mangInfoMapper.updateMangByCustId(mangInfos);
	}

}
