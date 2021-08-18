package com.work.task.sbjpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.work.task.sbjpa.dao.EmployeeDao;
import com.work.task.sbjpa.model.Employee;
import com.work.task.sbjpa.service.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertEmployeeReq(Employee employee) {
		employeeDao.insertEmployee(employee);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER)
	public void insertEmployeeNever(Employee employee) {
		employeeDao.insertEmployee(employee);
	}
	
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void insertEmployeeMand(Employee employee) {
		employeeDao.insertEmployee(employee);
	}
	
	@Override
	public void insertEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
	}
	
	@Override
	public void deleteEmployeeById(String empid) {
		employeeDao.deleteEmployeeById(empid);
	}

}