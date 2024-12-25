package com.itec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.itec.pojo.Operate;

public interface OperateMapper {
	// 通过合同id查询员工信息
	@Select("select * from M_OPERATE_TBL where OPER_NO = #{operNo}")
	List<Operate> seleceOperByCustId(String operNo);

	// 添加工程师
	int insertOper(List<Operate> operates);

	// 单独更新工程师
	int updateOperateByOperNo(List<Operate> operates);

	// 删除多余工程师
	int deleteOperatesNotInListById(List<Operate> operates);
}