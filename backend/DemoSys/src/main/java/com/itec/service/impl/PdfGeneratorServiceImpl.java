package com.itec.service.impl;

import java.sql.Connection;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itec.service.PdfGeneratorService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

	@Autowired
	private DataSource dataSource; // 使用 Spring 管理的数据源

	public JasperPrint generatePdfReport(String reportPath, Map<String, Object> parameters) throws JRException {
		try (Connection connection = dataSource.getConnection()) {
			// 加载预编译的 .jasper 文件
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

			// 使用连接作为数据源传递给 JasperFillManager
			return JasperFillManager.fillReport(jasperReport, parameters, connection);
		} catch (Exception e) {
			throw new JRException("Failed to generate PDF report: " + e.getMessage(), e);
		}
	}

}
