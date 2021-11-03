package com.bridgelabz.employeepayrollapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class EmployeePayrollAppApplication {

	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(EmployeePayrollAppApplication.class, args);
		log.info("Employee payroll app started in {} Environment",
				 context.getEnvironment().getProperty("environment"));
	}

}
