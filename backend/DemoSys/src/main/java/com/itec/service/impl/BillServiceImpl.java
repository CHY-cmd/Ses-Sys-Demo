package com.itec.service.impl;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itec.mapper.BillMapper;
import com.itec.pojo.Bill;
import com.itec.pojo.BillBillDtelRequest;
import com.itec.pojo.PdfFileDto;
import com.itec.pojo.vo.BillQuaryCondition;
import com.itec.pojo.vo.R;
import com.itec.service.BillService;
import com.itec.service.PdfGeneratorService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class BillServiceImpl implements BillService {
	@Autowired
	BillMapper billMapper;

	@Autowired
	private PdfGeneratorService pdfGeneratorService;

	@Override
	public R selectBillGeneral(Integer currentPage, Integer limit, BillQuaryCondition billQuaryCondition) {
		Integer startIndex = (currentPage - 1) * limit;
		List<Bill> selectBillGeneral = billMapper.selectBillGeneral(startIndex, limit, billQuaryCondition);
		Integer selectBillGeneralNum = billMapper.selectBillGeneralNum(billQuaryCondition);
		if (selectBillGeneralNum > 0) {
			return R.success().data("items", selectBillGeneral).data("itemsNum", selectBillGeneralNum);
		} else {
			return R.success().message("検索結果は0件です").data("itemsNum", selectBillGeneralNum);
		}
	}

	@Override
	public R selectBillBillDeltById(String billNo) {
		List<BillBillDtelRequest> selectBillBillDeltById = billMapper.selectBillBillDeltById(billNo);
		return R.success().data("items", selectBillBillDeltById);
	}

	@Override
	public R deleteBillById(String billNo) {
		int i = billMapper.deleteBillById(billNo);
		if (i > 0) {
			return R.success().message("削除が完了しました");
		} else {
			return R.error().message("削除に失敗しました");
		}
	}

	@Override
	public int insertBill(Bill bill) {
		return billMapper.insertBill(bill);
	}

	@Override
	public R selectAllBill() {
		List<Bill> selectAllBill = billMapper.selectAllBill();
		return R.success().data("items", selectAllBill);
	}

	private static final String REPORT_PATH = "src/main/resources/reports/SesBill.jasper"; // 在这里配置报表路径

	@Override
	public PdfFileDto generateBillPdf(String billNo) throws Exception {
		// 查询账单详情列表，并只保留第一个元素
		Optional<BillBillDtelRequest> firstBillDetailOpt = billMapper.selectBillBillDeltById(billNo).stream()
				.findFirst();
		if (!firstBillDetailOpt.isPresent()) {
			throw new IllegalArgumentException("No bills found for billNo: " + billNo);
		}
		BillBillDtelRequest billDetails = firstBillDetailOpt.get();
		// 构建参数映射
		Map<String, Object> parameters = buildParameters(billNo, billDetails);
		// 生成 JasperPrint 对象并导出为 PDF 字节数组
		JasperPrint jasperPrint = pdfGeneratorService.generatePdfReport(REPORT_PATH, parameters);
		byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
		// 使用 generateFileName 方法生成文件名，但不改变返回值
		String fileName = generateFileName(billDetails.getBillEndtdt(), billDetails.getBillCustNm());
		return new PdfFileDto(pdfBytes, fileName);
	}

	@Override
	public byte[] generateBatchBillPdfs(List<String> billNos) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);

		for (String billNo : billNos) {
			// 生成 PDF 字节数组
			PdfFileDto pdfBytes = generateBillPdf(billNo);

			// 创建 ZipEntry 并写入 PDF 文件内容
			ZipEntry zipEntry = new ZipEntry(pdfBytes.getFileName());
			zos.putNextEntry(zipEntry);
			zos.write(pdfBytes.getPdfBytes());
			zos.closeEntry();
		}

		zos.finish();
		zos.close();
		return baos.toByteArray();
	}

	/**
	 * 构建报表所需的参数映射。
	 */
	private Map<String, Object> buildParameters(String billNo, BillBillDtelRequest billDetails) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("BILL_NO", billNo); // 使用前端传递的 billNo
		// 如果有其他固定的或动态的参数，可以在这里添加
		return parameters;
	}

	/**
	 * 根据账单结束日期和客户名称生成文件名。
	 */
	public String generateFileName(Date billEndtdt, String billCustNm) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String yearMonth = sdf.format(billEndtdt);
		return String.format("[%s]御請求書_%s御中.pdf", yearMonth, billCustNm);
	}

	@Override
	public int updateBillById(Bill bill) {
		return billMapper.updateBillById(bill);
	}

}
