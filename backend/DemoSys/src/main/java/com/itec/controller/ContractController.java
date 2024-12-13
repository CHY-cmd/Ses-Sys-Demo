package com.itec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itec.pojo.vo.ContractQuaryCondition;
import com.itec.pojo.vo.R;
import com.itec.service.ContractService;

@RestController
@RequestMapping("/contract")
@CrossOrigin
public class ContractController {
	@Autowired
	ContractService contractService;

//组合分页查询概览
	@PostMapping("/selectContrGeneral/{currentPage}/{limit}")
	public R selectContrGeneral(@PathVariable("currentPage") Integer currentPage, @PathVariable("limit") Integer limit,
			@RequestBody ContractQuaryCondition contractQuaryCondition) {
		return contractService.selectContrGeneral(currentPage, limit, contractQuaryCondition);
	}

	// 通过id合并查询
	@GetMapping("/selectContrOperById/{contrNo}")
	public R selectContrGeneral(@PathVariable("contrNo") String contrNo) {
		return contractService.selectContrOperById(contrNo);
	}

	// 逻辑删除
	@Transactional
	@DeleteMapping("/selectContrOperById/{contrNo}")
	public R deleteContrById(@PathVariable("contrNo") String contrNo) {
		return contractService.deleteContrById(contrNo);
	}

}
