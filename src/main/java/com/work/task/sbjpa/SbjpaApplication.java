package com.work.task.sbjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.work.task.sbjpa.model.Employee;
import com.work.task.sbjpa.model.EmployeeHealthInsurance;
import com.work.task.sbjpa.service.OrganizationService;

  

@SpringBootApplication
public class SbjpaApplication {

	public static void main(String[] args) {
		 SpringApplication.run(SbjpaApplication.class, args);

	}

}
