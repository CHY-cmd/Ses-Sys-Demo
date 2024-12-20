package com.itec;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.itec.mapper")
@EnableTransactionManagement
public class DemoSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSysApplication.class, args);
	}

}
