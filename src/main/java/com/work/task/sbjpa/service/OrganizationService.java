package com.work.task.sbjpa.service;

import com.work.task.sbjpa.model.Employee;
import com.work.task.sbjpa.model.EmployeeHealthInsurance;
import com.work.task.sbjpa.service.impl.InvalidInsuranceAmountException;

public interface OrganizationService {

	public void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance, String propagation) throws InvalidInsuranceAmountException;
	
	public void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

}
