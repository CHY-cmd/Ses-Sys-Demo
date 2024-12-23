package com.itec.service;

import org.springframework.stereotype.Service;

import com.itec.pojo.Staff;
import com.itec.pojo.vo.R;
import com.itec.pojo.vo.StaffQuaryCondition;

@Service
public interface StaffService {
	R selectAllStaff();

	R deleteStaffByID(String staffId);

	R selectStaffInfo(Integer currentPage, Integer limit, StaffQuaryCondition staffQuaryCondition);

	R insertStaff(Staff staff);

	R updateStaffById(Staff staff);

	R selectStaffById(String staffId);

}
