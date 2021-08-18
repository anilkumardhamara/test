package com.work.task.sbjpa.service;

import com.work.task.sbjpa.model.Employee;

public interface EmployeeService {
	
	void insertEmployeeReq(Employee emp);

	void insertEmployeeNever(Employee emp);

	void insertEmployeeMand(Employee emp);
	
	void insertEmployee(Employee emp);

	void deleteEmployeeById(String empid);
}