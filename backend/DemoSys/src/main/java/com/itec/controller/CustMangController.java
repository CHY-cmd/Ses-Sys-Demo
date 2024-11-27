package com.itec.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itec.pojo.MangInfo;
import com.itec.pojo.vo.GeneralQuaryCondition;
import com.itec.pojo.vo.R;
import com.itec.service.impl.CustMangGeneralServiceImpl;
import com.itec.service.impl.MangInfoServiceImpl;
import com.itec.util.IDUtil;

@RestController
@RequestMapping("/general")
@CrossOrigin
public class CustMangController {

	@Autowired
	CustMangGeneralServiceImpl custMangGeneralServiceImpl;
	@Autowired
	MangInfoServiceImpl mangInfoServiceImpl;

	// 组合分页查询概览
	@PostMapping("/selectGeneral/{currentPage}/{limit}")
	public R selectGeneral(@PathVariable("currentPage") Integer currentPage, @PathVariable("limit") Integer limit,
			@RequestBody GeneralQuaryCondition generalQuaryCondition) {

		return custMangGeneralServiceImpl.selectGeneral(currentPage, limit, generalQuaryCondition);
	}

	// 单独添加经理信息（判断更新或新增时）
	@PostMapping("/insertMang/{managerCustId}")
	@Transactional
	public R insertMang(@PathVariable String managerCustId, @RequestBody List<MangInfo> mangInfo) {
		for (MangInfo mangInfo1 : mangInfo) {
			String managerCd = IDUtil.getID();
			String managerChrcd = IDUtil.getID();
			mangInfo1.setManagerCd(managerCd);
			mangInfo1.setManagerChrcd(managerChrcd);
			mangInfo1.setManagerCustId(managerCustId);
			mangInfo1.setManagerCrdDt(LocalDateTime.now());
			mangInfo1.setManagerUpdDt(LocalDateTime.now());
		}
		int i = mangInfoServiceImpl.insertMang(mangInfo);
		if (i > 0) {
			return R.success().message("登録成功");
		} else {
			return R.error().message("登録エラー");
		}
	}

	// 单独更新经理信息
	@PutMapping("/updateMang")
	@Transactional
	public R updateMangByCustId(@RequestBody List<MangInfo> mangInfos) {
		for (MangInfo mangInfo : mangInfos) {
			mangInfo.setManagerUpdDt(LocalDateTime.now());
		}
		int i = mangInfoServiceImpl.updateMangByCustId(mangInfos);
		if (i > 0) {
			return R.success().message("更新成功");
		} else {
			return R.error().message("更新エラー");
		}
	}
}
