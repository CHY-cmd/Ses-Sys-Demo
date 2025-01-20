package com.itec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itec.pojo.Bill;
import com.itec.pojo.PdfFileDto;
import com.itec.pojo.vo.BillQuaryCondition;
import com.itec.pojo.vo.R;

@Service
public interface BillService {
	R selectBillGeneral(Integer currentPage, Integer limit, BillQuaryCondition billQuaryCondition);

	R selectBillBillDeltById(String billNo);

	R deleteBillById(String billNo);

	R selectAllBill();

	int insertBill(Bill bill);

	PdfFileDto generateBillPdf(String billNo) throws Exception;

	byte[] generateBatchBillPdfs(List<String> billNos) throws Exception;

	int updateBillById(Bill bill);
}
