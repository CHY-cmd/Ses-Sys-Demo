package com.itec.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperPrint;

@Service
public interface PdfGeneratorService {
	JasperPrint generatePdfReport(String reportPath, Map<String, Object> parameters) throws Exception;
}
