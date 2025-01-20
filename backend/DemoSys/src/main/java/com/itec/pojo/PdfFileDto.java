package com.itec.pojo;

public class PdfFileDto {
	private byte[] pdfBytes;
	private String fileName;

	public PdfFileDto(byte[] pdfBytes, String fileName) {
		this.pdfBytes = pdfBytes;
		this.fileName = fileName;
	}

	public byte[] getPdfBytes() {
		return pdfBytes;
	}

	public void setPdfBytes(byte[] pdfBytes) {
		this.pdfBytes = pdfBytes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
