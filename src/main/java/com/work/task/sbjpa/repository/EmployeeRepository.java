package com.work.task.sbjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.work.task.sbjpa.model.EmployeeTest;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeTest, Long>{
	
	


	@Query(value ="select u.* from employee_test u where u.employee_id=?1",nativeQuery = true)
	public EmployeeTest getEmployeeById(int id);
	
	@Query(value ="select u from EmployeeTest u where u.DisplayName = :displayName")
	public EmployeeTest getEmployeeByName(@Param("displayName") String name);



}