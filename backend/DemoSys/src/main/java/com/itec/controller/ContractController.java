package com.itec.controller;

import java.util.Date;
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

import com.itec.pojo.ContrOperRequest;
import com.itec.pojo.Contract;
import com.itec.pojo.Operate;
import com.itec.pojo.vo.ContractQuaryCondition;
import com.itec.pojo.vo.R;
import com.itec.service.ContractService;
import com.itec.service.OperateService;

@RestController
@RequestMapping("/contract")
@CrossOrigin
public class ContractController {
	@Autowired
	ContractService contractService;
	@Autowired
	OperateService operateService;

//组合分页查询概览
	@Transactional
	@PostMapping("/selectContrGeneral/{currentPage}/{limit}")
	public R selectContrGeneral(@PathVariable("currentPage") Integer currentPage, @PathVariable("limit") Integer limit,
			@RequestBody ContractQuaryCondition contractQuaryCondition) {
		return contractService.selectContrGeneral(currentPage, limit, contractQuaryCondition);
	}

	// 通过id合并查询
	@Transactional
	@GetMapping("/selectContrOperById/{contrNo}")
	public R selectContrGeneral(@PathVariable("contrNo") String contrNo) {
		return contractService.selectContrOperById(contrNo);
	}

	// 逻辑删除
	@Transactional
	@DeleteMapping("/deleteContrById/{contrNo}")
	public R deleteContrById(@PathVariable("contrNo") String contrNo) {
		return contractService.deleteContrById(contrNo);
	}

	// 单独添加工程师信息（判断更新或新增时）
	@PostMapping("/insertOper/{operNo}")
	@Transactional
	public R insertOper(@PathVariable String operNo, @RequestBody List<Operate> operate) {
		for (Operate operate1 : operate) {
			operate1.setOperCrdDt(new Date());
			operate1.setOperUpdDt(new Date());
		}
		int i = operateService.insertOper(operate);
		if (i > 0) {
			return R.success().message("登録成功");
		} else {
			return R.error().message("登録エラー");
		}
	}

	// 单独更新工程师信息
	@PutMapping("/updateOper")
	@Transactional
	public R updateOperateByOperNo(@RequestBody List<Operate> operates) {
		for (Operate operate : operates) {
			operate.setOperUpdDt(new Date());
		}
		int i = operateService.updateOperateByOperNo(operates);
		if (i > 0) {
			return R.success().message("更新成功");
		} else {
			return R.error().message("更新エラー");
		}
	}

	// 单独更新合同信息
	@PutMapping("/updateContr")
	@Transactional
	public R updateContrById(@RequestBody Contract contract) {
		contract.setContrUpdDt(new Date());
		int i = contractService.updateContrById(contract);
		if (i > 0) {
			return R.success().message("更新成功");
		} else {
			return R.error().message("更新エラー");
		}
	}

	// 新增合同工程师
	@PostMapping("/insertContrOper")
	@Transactional
	public R insertContrOper(@RequestBody ContrOperRequest contrOperRequest) {
		Contract contract = contrOperRequest.getContract();
		// 设置合同表的部分字段为工程师表的第一组数据
		if (!contrOperRequest.getOperates().isEmpty()) {
			Operate firstOperate = contrOperRequest.getOperates().get(0);
			contract.setContrEngineerNm(firstOperate.getOperEngineerNm());
		}
		contract.setContrDelflg("0");
		contract.setContrCrdDt(new Date());
		contract.setContrUpdDt(new Date());

		// 输入多条工程师数据 赋值合同表中的contrNo和contrProjectNm
		List<Operate> operates = contrOperRequest.getOperates();
		for (Operate operate : operates) {
			operate.setOperNo(contract.getContrNo());
			operate.setOperProjectNm(contract.getContrProjectNm());
			operate.setOperUpdDt(new Date());
			operate.setOperCrdDt(new Date());
		}

		int a = contractService.insertContr(contract);
		int b = operateService.insertOper(operates);
		if (a > 0 && b > 0) {
			return R.success().message("登録成功");
		} else {
			return R.error().message("登録エラー");
		}
	}

	@GetMapping("/selectTest/{contrNo}")
	public R selectTest(@PathVariable("contrNo") String contrNo) {
		return contractService.selectTest(contrNo);
	}

}
