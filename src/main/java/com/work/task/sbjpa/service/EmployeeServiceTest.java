package com.work.task.sbjpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.task.sbjpa.controller.EmployeeRESTController;
import com.work.task.sbjpa.dao.EmployeeDaoHelper;
import com.work.task.sbjpa.dto.EmployeeDto;
import com.work.task.sbjpa.model.EmployeeTest;
import com.work.task.sbjpa.model.Product;
import com.work.task.sbjpa.repository.EmployeeRepository;

@Service
public class EmployeeServiceTest {
	String methodName="";
	String  CLASS_NAME ="EmployeeService";
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeDaoHelper employeeDaoHelper;
	
	Logger logger = LoggerFactory.getLogger(EmployeeRESTController.class);

	public EmployeeTest addEmployee(EmployeeDto employeeDto){
	
		
		methodName = "addEmployee";
		logger.info(CLASS_NAME + " :: " + methodName + " :: " + " started");
		EmployeeTest employee = new EmployeeTest();

		//employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setDisplayName(employeeDto.getDisplayName());
		employee.setEmail(employeeDto.getEmail());
		
		employeeRepository.save(employee);
		
		logger.info(CLASS_NAME + " :: " + methodName + " :: " + " ended");

		return employee;
		
	
	}
	
	@Transactional
	public EmployeeTest addEmployeeByTransaction(EmployeeDto employeeDto){
			
		methodName = "addEmployeeByTransaction";
		logger.info(CLASS_NAME + " :: " + methodName + " :: " + " started");
		EmployeeTest employee = new EmployeeTest();

		//employee.setEmployeeId(employeeDto.getEmployeeId());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setDisplayName(employeeDto.getDisplayName());
		employee.setEmail(employeeDto.getEmail());	
		
		employeeDaoHelper.addEmployee(employee);
		
		   if (employeeDto.getDisplayName().equals("emp1")) {
				throw new RuntimeException("thowing exception to test transaction rollback");
			}
		Product product = new Product();

		product.setPname(employeeDto.getProductName());
		product.setCategory(employeeDto.getCatagory());
		product.setPrice(employeeDto.getPrice());
		employeeDaoHelper.addProduct(product);
		
		logger.info(CLASS_NAME + " :: " + methodName + " :: " + " ended");

		return employee;			
	}	
	

	
}
