package com.itec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itec.pojo.MangInfo;

@Mapper
public interface MangInfoMapper {
	// 通过客户id查询经理信息
	@Select("select * from mang_tbl")
	List<MangInfo> seleceMangInfoByCustId(String managerCustId);

	// 添加经理信息
	int insertMang(List<MangInfo> mangInfo);

	// 更新经理表
	int updateMangByCustId(@Param("list") List<MangInfo> mangInfos);

}