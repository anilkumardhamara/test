package com.work.task.sbjpa.dao;

import com.work.task.sbjpa.model.Employee;

public interface EmployeeDao {
	void insertEmployee(Employee cus);

	void deleteEmployeeById(String empid);
}