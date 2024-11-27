package com.itec;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itec.mapper")
public class DemoSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSysApplication.class, args);
	}

}
