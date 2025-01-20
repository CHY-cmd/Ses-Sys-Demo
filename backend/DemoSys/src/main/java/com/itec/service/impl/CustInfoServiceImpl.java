package com.itec.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itec.mapper.CustInfoMapper;
import com.itec.pojo.BankInfo;
import com.itec.pojo.CustInfo;
import com.itec.pojo.vo.R;
import com.itec.service.CustInfoService;

@Service
public class CustInfoServiceImpl implements CustInfoService {

	@Autowired
	CustInfoMapper custInfoMapper;

	@Override
	public R selectAllCustInfos() {
		List<CustInfo> selectAllCustInfos = custInfoMapper.selectAllCustInfos();
		return R.success().data("items", selectAllCustInfos);
	}

	@Override
	public R deleteCustByID(String custId) {
		int i = custInfoMapper.deleteCustByID(custId);
		if (i > 0) {
			return R.success().message("削除が完了しました");
		} else {
			return R.success().message("削除に失敗しました");
		}
	}

	@Override
	public int insertCust(CustInfo custInfo) {
		return custInfoMapper.insertCust(custInfo);
	}

	@Override
	public R selectBank() {
		List<BankInfo> selectBank = custInfoMapper.selectBank();
		return R.success().data("items", selectBank);
	}

	@Override
	public R selectCustBank() {
		List<BankInfo> selectCustBank = custInfoMapper.selectCustBank();
		return R.success().data("items", selectCustBank);
	}

	@Override
	public R updateCustByCustId(CustInfo custInfo) {
		custInfo.setCustUpdDt(LocalDateTime.now());
		int i = custInfoMapper.updateCustByCustId(custInfo);
		if (i > 0) {
			return R.success().message("更新成功");
		} else {
			return R.error().message("更新エラー");
		}
	}

	@Override
	public R selectCustByNm(String custNm) {
		List<CustInfo> selectCustByNm = custInfoMapper.selectCustByNm(custNm);
		return R.success().data("items", selectCustByNm);
	}

}
