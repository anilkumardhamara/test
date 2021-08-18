package com.work.task.sbjpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.task.sbjpa.model.Employee;
import com.work.task.sbjpa.model.EmployeeHealthInsurance;
import com.work.task.sbjpa.service.EmployeeService;
import com.work.task.sbjpa.service.HealthInsuranceService;
import com.work.task.sbjpa.service.OrganizationService;



@Service
public class OrganzationServiceImpl implements OrganizationService {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	HealthInsuranceService healthInsuranceService;

	@Override
	@Transactional(rollbackFor = InvalidInsuranceAmountException.class)
	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance, String propagation) 
			throws InvalidInsuranceAmountException{
			if(propagation=="REQUIRED")
				employeeService.insertEmployeeReq(employee);
			else if(propagation=="NEVER")
				employeeService.insertEmployeeNever(employee);
			else if(propagation=="MANDATORY")
				employeeService.insertEmployeeMand(employee);
			else
				employeeService.insertEmployee(employee);
			
			if (employee.getEmpId().equals("emp1")) {
				throw new RuntimeException("thowing exception to test transaction rollback");
			}
		
		try {
			healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
		} catch (InvalidInsuranceAmountException e) {
			throw new InvalidInsuranceAmountException("Exception is thrown");
		}
	}


	@Override
	@Transactional
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
		employeeService.deleteEmployeeById(employee.getEmpId());
		healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
	}
}