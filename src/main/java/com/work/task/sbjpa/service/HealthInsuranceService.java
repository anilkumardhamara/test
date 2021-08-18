package com.work.task.sbjpa.service;

 import com.work.task.sbjpa.model.EmployeeHealthInsurance;
import com.work.task.sbjpa.service.impl.InvalidInsuranceAmountException;

public interface HealthInsuranceService {
	void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance)  throws InvalidInsuranceAmountException;

	void deleteEmployeeHealthInsuranceById(String empid);
}