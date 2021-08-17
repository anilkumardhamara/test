package com.work.task.sbjpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.task.sbjpa.controller.EmployeeRESTController;
import com.work.task.sbjpa.dto.EmployeeDto;
import com.work.task.sbjpa.model.Employee;
import com.work.task.sbjpa.repository.EmployeeRepository;

@Service
public class EmployeeService {
	String methodName="";
	String  CLASS_NAME ="EmployeeService";
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	Logger logger = LoggerFactory.getLogger(EmployeeRESTController.class);

	public Employee addEmployee(EmployeeDto employeeDto){
	
		
		methodName = "saveCardDetails";
		logger.info(CLASS_NAME + " :: " + methodName + " :: " + "saveCardDetails started");
		Employee employee = new Employee();
		logger.info(CLASS_NAME + " :: " + methodName + " :333: " + "saveCardDetails started");

		//employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setDisplayName(employeeDto.getDisplayName());
		employee.setEmail(employeeDto.getEmail());
		logger.info(CLASS_NAME + " :: " + methodName + " :: " + "saveCardDetails ended"+employeeDto.getDisplayName());
		 employeeRepository.save(employee);
		logger.info(CLASS_NAME + " :: " + methodName + " :: " + "saveCardDetails ended");

		 return employee;
		
	
	}
}
