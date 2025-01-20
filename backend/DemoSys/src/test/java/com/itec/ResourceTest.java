package com.itec;

import java.io.InputStream;

public class ResourceTest {
	public static void main(String[] args) {
		// 测试 jasperreports_extension.properties 文件是否可访问
		InputStream propertiesStream = ResourceTest.class.getResourceAsStream("/jasperreports_extension.properties");
		if (propertiesStream != null) {
			System.out.println("jasperreports_extension.properties is accessible.");
		} else {
			System.err.println("jasperreports_extension.properties is not accessible.");
		}

		// 测试 fonts.xml 文件是否可访问
		InputStream fontsXmlStream = ResourceTest.class.getResourceAsStream("/fonts/fonts.xml");
		if (fontsXmlStream != null) {
			System.out.println("fonts/fonts.xml is accessible.");
		} else {
			System.err.println("fonts/fonts.xml is not accessible.");
		}

		// 测试 MSGothic 字体文件是否可访问
		InputStream fontStream = ResourceTest.class.getResourceAsStream("/fonts/MSGothic.ttc");
		if (fontStream != null) {
			System.out.println("MSGothic.ttc is accessible.");
		} else {
			System.err.println("MSGothic.ttc is not accessible.");
		}

		System.out.println(
				"Attempting to load MSGothic.ttc from: " + ResourceTest.class.getResource("/fonts/MSGothic.ttc"));
	}
}
