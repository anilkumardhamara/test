package com.work.task.sbjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.work.task.sbjpa.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	


	@Query(value ="select u.* from employee u where u.employee_id=?1",nativeQuery = true)
	public Employee getEmployeeById(int id);
	
	@Query(value ="select u from Employee u where u.DisplayName = :displayName")
	public Employee getEmployeeByName(@Param("displayName") String name);



}