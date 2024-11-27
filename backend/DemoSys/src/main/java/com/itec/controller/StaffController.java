package com.itec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itec.pojo.vo.R;
import com.itec.pojo.vo.StaffQuaryCondition;
import com.itec.service.StaffService;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;

	// 分页查询
	@PostMapping("/staffInfo/{currentPage}/{limit}")
	public R selectStaffInfo(@PathVariable("currentPage") Integer currentPage, @PathVariable("limit") Integer limit,
			@RequestBody StaffQuaryCondition staffQuaryCondition) {
		return staffService.selectStaffInfo(currentPage, limit, staffQuaryCondition);
	}

	// 逻辑删除
	@Transactional
	@DeleteMapping("/deleteStaffByID/{staffId}")
	public R deleteStaffByID(@PathVariable("staffId") String staffId) {
		return staffService.deleteStaffByID(staffId);
	}
}
