package com.itec.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itec.mapper.StaffMapper;
import com.itec.pojo.Staff;
import com.itec.pojo.vo.R;
import com.itec.pojo.vo.StaffQuaryCondition;
import com.itec.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	StaffMapper staffMapper;

	@Override
	public R selectAllStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R deleteStaffByID(String staffId) {
		int i = staffMapper.deleteStaffByID(staffId);
		if (i > 0) {
			return R.success().message("削除が完了しました");
		} else {
			return R.success().message("削除に失敗しました");
		}
	}

	@Override
	public R selectStaffInfo(Integer currentPage, Integer limit, StaffQuaryCondition staffQuaryCondition) {
		Integer startIndex = (currentPage - 1) * limit;
		List<Staff> selectStaffInfo = staffMapper.selectStaffInfo(startIndex, limit, staffQuaryCondition);
		Integer selectStaffNum = staffMapper.selectStaffNum(staffQuaryCondition);
		if (selectStaffNum > 0) {
			return R.success().data("items", selectStaffInfo).data("itemsNum", selectStaffNum);
		} else {
			return R.success().message("検索結果は0件です").data("itemsNum", selectStaffNum);
		}
	}

	@Override
	public R insertStaff(Staff staff) {
		staff.setStaffCreDt(new Date());
		staff.setStaffUpdDt(new Date());
		staff.setStaffDelflg("0");
		int i = staffMapper.insertStaff(staff);
		if (i > 0) {
			return R.success().message("登録成功");
		} else {
			return R.error().message("登録エラー");
		}
	}

	@Override
	public R updateStaffById(Staff staff) {
		staff.setStaffUpdDt(new Date());
		int i = staffMapper.updateStaffById(staff);
		if (i > 0) {
			return R.success().message("更新成功");
		} else {
			return R.error().message("更新エラー");
		}
	}

	@Override
	public R selectStaffById(String staffId) {
		List<Staff> selectStaffById = staffMapper.selectStaffById(staffId);
		return R.success().data("items", selectStaffById);
	}

}
