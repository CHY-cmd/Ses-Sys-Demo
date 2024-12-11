package com.itec.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itec.pojo.CustInfo;
import com.itec.pojo.CustMangRequest;
import com.itec.pojo.MangInfo;
import com.itec.pojo.vo.R;
import com.itec.service.impl.CustInfoServiceImpl;
import com.itec.service.impl.CustMangGeneralServiceImpl;
import com.itec.service.impl.MangInfoServiceImpl;
import com.itec.util.IDUtil;

@RestController
@RequestMapping("/details")
@CrossOrigin
public class CustInfoController {

	@Autowired
	CustInfoServiceImpl custInfoServiceImpl;
	@Autowired
	MangInfoServiceImpl mangInfoServiceImpl;
	@Autowired
	CustMangGeneralServiceImpl custMangGeneralServiceImpl;

	// 查询所有
	@GetMapping("/selectAllCustInfos")
	public R selectAllCustInfos() {
		return custInfoServiceImpl.selectAllCustInfos();
	}

	// 逻辑删除客户
	@DeleteMapping("/deleteCustByID/{custId}")
	@Transactional
	public R deleteCustByID(@PathVariable("custId") String custId) {
		return custInfoServiceImpl.deleteCustByID(custId);
	};

	// 新增
	@PostMapping("/insertCustMang")
	@Transactional
	public R insertCustMang(@RequestBody CustMangRequest custMangRequest) {
		CustInfo custInfo = custMangRequest.getCustInfo();
		String custId = custInfo.getCustId();
//		String custId = IDUtil.getID();
//		custInfo.setCustId(custId);
		custInfo.setCustDelflg(false);
		custInfo.setCustCrdDt(LocalDateTime.now());
		custInfo.setCustUpdDt(LocalDateTime.now());

		// 输入多条经理数据
		List<MangInfo> mangInfos = custMangRequest.getMangInfo();
		String managerCustId = custId;
		for (MangInfo mangInfo : mangInfos) {
			String managerCd = IDUtil.getID();
			String managerChrcd = IDUtil.getID();
			mangInfo.setManagerCd(managerCd);
			mangInfo.setManagerChrcd(managerChrcd);
			mangInfo.setManagerCustId(managerCustId);
			mangInfo.setManagerCrdDt(LocalDateTime.now());
			mangInfo.setManagerUpdDt(LocalDateTime.now());
		}

		int a = custInfoServiceImpl.insertCust(custInfo);
		int b = mangInfoServiceImpl.insertMang(mangInfos);
		if (a > 0 && b > 0) {
			return R.success().message("登録成功");
		} else {
			return R.error().message("登録エラー");
		}
	}

//	// 更新两表
//	@PutMapping("/updateCustMang")
//	@Transactional
//	public R updateCustMang(@RequestBody CustMangRequest custMangRequest) {
//		CustInfo custInfo = custMangRequest.getCustInfo();
//		custInfo.setCustUpdDt(LocalDateTime.now());
//
//		List<MangInfo> mangInfos = custMangRequest.getMangInfo();
//		for (MangInfo mangInfo : mangInfos) {
//			mangInfo.setManagerUpdDt(LocalDateTime.now());
//		}
//		int a = custInfoServiceImpl.updateCustByCustId(custInfo);
//		int b = mangInfoServiceImpl.updateMangByCustId(mangInfos);
//		if (a > 0 && b > 0) {
//			return R.success().message("更新成功");
//		} else {
//			return R.error().message("更新エラー");
//		}
//	};

	// 单独更新客户表
	@PutMapping("/updateCust")
	@Transactional
	public R updateCustByCustId(@RequestBody CustInfo custInfo) {
		return custInfoServiceImpl.updateCustByCustId(custInfo);
	}

	// 查询银行信息
	@GetMapping("/selectBank")
	public R selectBank() {
		return custInfoServiceImpl.selectBank();
	}

	// 查客户银行
	@GetMapping("/selectCustBank")
	public R selectCustBank() {
		return custInfoServiceImpl.selectCustBank();
	}

	// 通过id查询客户、经理信息
	@GetMapping("/selectDetailByCustId/{custId}")
	public R selectDetailByCustId(@PathVariable("custId") String custId) {
		return custMangGeneralServiceImpl.selectDetailByCustId(custId);
	}
}
