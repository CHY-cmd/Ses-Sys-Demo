package com.itec.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.itec.pojo.Bill;
import com.itec.pojo.BillBillDtelRequest;
import com.itec.pojo.BillDetl;
import com.itec.pojo.PdfFileDto;
import com.itec.pojo.vo.BillQuaryCondition;
import com.itec.pojo.vo.R;
import com.itec.service.BillDetlService;
import com.itec.service.BillService;

@RestController
@RequestMapping("/bill")
@CrossOrigin
public class BillController {
	@Autowired
	BillService billService;
	@Autowired
	BillDetlService billDetlService;

	// 组合分页查询概览
	@Transactional
	@PostMapping("/selectBillGeneral/{currentPage}/{limit}")
	public R selectBillGeneral(@PathVariable("currentPage") Integer currentPage, @PathVariable("limit") Integer limit,
			@RequestBody BillQuaryCondition billQuaryCondition) {
		return billService.selectBillGeneral(currentPage, limit, billQuaryCondition);
	}

	// 通过id合并查询
	@Transactional
	@GetMapping("/selectBillBillDeltById/{billNo}")
	public R selectBillBillDeltById(@PathVariable("billNo") String billNo) {
		return billService.selectBillBillDeltById(billNo);
	}

	// 单个生成
	@GetMapping("/generateSesBill/{billNo}")
	public ResponseEntity<byte[]> generateSesBillPdf(@PathVariable String billNo) throws Exception {
		PdfFileDto pdfFileDto = billService.generateBillPdf(billNo);
		byte[] pdfBytes = pdfFileDto.getPdfBytes();
		String fileName = pdfFileDto.getFileName();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.add("Content-Disposition", "attachment; " + encodeFileName(fileName));
		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	}

	private String encodeFileName(String fileName) {
		try {
			// 使用 RFC 5987 标准编码文件名
			return "filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
		} catch (Exception e) {
			// 如果编码失败，返回默认文件名
			return "filename=\"" + sanitizeFileName(fileName) + "\"";
		}
	}

	private String sanitizeFileName(String fileName) {
		// 移除非 ASCII 字符作为备用措施
		return fileName.replaceAll("[^\\x20-\\x7e]", "_");
	}

	// 批量生成
	@PostMapping("/generateSesBill/billNos")
	public ResponseEntity<byte[]> generateBatchSesBillPdfs(@RequestBody List<String> billNos) throws Exception {
		byte[] zipBytes = billService.generateBatchBillPdfs(billNos);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "bills.zip");

		return new ResponseEntity<>(zipBytes, headers, HttpStatus.OK);
	}

	// 逻辑删除
	@Transactional
	@DeleteMapping("/deleteBillById/{billNo}")
	public R deleteBillById(@PathVariable("billNo") String billNo) {
		return billService.deleteBillById(billNo);
	}

	// 查询所有账单
	@Transactional
	@GetMapping("/selectAllBill")
	public R selectAllBill() {
		return billService.selectAllBill();
	}

	// 单独添加明细（判断更新或新增时）
	@PostMapping("/insertBillDetl/{billdetlNo}")
	@Transactional
	public R insertBillDetl(@PathVariable String billdetlNo, @RequestBody List<BillDetl> billDetl) {
		for (BillDetl billDetl1 : billDetl) {
			billDetl1.setBilldetlCrdDt(new Date());
			billDetl1.setBilldetlUpdDt(new Date());
		}
		int i = billDetlService.insertBillDetl(billDetl);
		if (i > 0) {
			return R.success().message("登録成功");
		} else {
			return R.error().message("登録エラー");
		}
	}

	// 单独更新明细
	@PutMapping("/updateBillDetl")
	@Transactional
	public R updateBillDetlByBillDetlNo(@RequestBody List<BillDetl> billDetls) {
		for (BillDetl billDetl : billDetls) {
			billDetl.setBilldetlUpdDt(new Date());
		}
		int i = billDetlService.updateBillDetlByBillDetlNo(billDetls);
		if (i > 0) {
			return R.success().message("更新成功");
		} else {
			return R.error().message("更新エラー");
		}
	}

	// 单独更新账单信息
	@PutMapping("/updateBill")
	@Transactional
	public R updateBillById(@RequestBody Bill bill) {
		bill.setBillUpdDt(new Date());
		int i = billService.updateBillById(bill);
		if (i > 0) {
			return R.success().message("更新成功");
		} else {
			return R.error().message("更新エラー");
		}
	}

	// 新增账单及明细
	@PostMapping("/insertBillBillDetl")
	@Transactional
	public R insertBillBillDetl(@RequestBody BillBillDtelRequest billBillDtelRequest) {
		Bill bill = billBillDtelRequest.getBill();
		// 设置部分字段为明细表的第一组数据
		if (!billBillDtelRequest.getBillDetls().isEmpty()) {
			BillDetl firstBillDetl = billBillDtelRequest.getBillDetls().get(0);
			bill.setBillEngineerNm(firstBillDetl.getBilldetlEngineerNm());
		}
		bill.setBillDelflg("0");
		bill.setBillCrdDt(new Date());
		bill.setBillUpdDt(new Date());

		// 输入多条明细数据 赋值主表中的billNo
		List<BillDetl> billDetls = billBillDtelRequest.getBillDetls();
		for (BillDetl billDetl : billDetls) {
			billDetl.setBilldetlNo(bill.getBillNo());
			billDetl.setBilldetlCrdDt(new Date());
			billDetl.setBilldetlUpdDt(new Date());
		}

		int a = billService.insertBill(bill);
		int b = billDetlService.insertBillDetl(billDetls);
		if (a > 0 && b > 0) {
			return R.success().message("登録成功");
		} else {
			return R.error().message("登録エラー");
		}
	}
}
