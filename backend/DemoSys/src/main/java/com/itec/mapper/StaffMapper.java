package com.itec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itec.pojo.Staff;
import com.itec.pojo.vo.StaffQuaryCondition;

public interface StaffMapper {

	// 查询所有
	@Select("select * from STAFF_TBL where STAFF_DELFLG = 0")
	List<Staff> selectAllStaff();

	// 逻辑删除
	@Delete("update STAFF_TBL set STAFF_DELFLG = 1,STAFF_DELDT=CONVERT_TZ(NOW(), '+00:00', '+09:00') where STAFF_DELFLG = 0 and STAFF_ID = #{staffId}")
	int deleteStaffByID(@Param("staffId") String staffId);

	// 分页查询概览
	List<Staff> selectStaffInfo(@Param("startIndex") Integer startIndex, @Param("limit") Integer limit,
			@Param("staffQuaryCondition") StaffQuaryCondition staffQuaryCondition);

	// 分页查询页数
	Integer selectStaffNum(@Param("staffQuaryCondition") StaffQuaryCondition staffQuaryCondition);
}