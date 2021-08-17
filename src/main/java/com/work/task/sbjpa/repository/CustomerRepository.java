package com.work.task.sbjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.task.sbjpa.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	
	@Query(value ="select u.* from customer u where u.id=?1 order  by age, display_name",nativeQuery = true)
	public List<Customer> findByCustomerID(@Param("id") long id);
	
	@Query(value ="select u.* from customer u order  by age, display_name",nativeQuery = true)
	public List<Customer> findAllCustomers();
	
	@Query(value ="select distinct u.display_name from customer u ",nativeQuery = true)
	public List<String> findAllDistinctCustomerNames();
	
	@Query(value ="select u.* from customer u where u.age>?1 order  by age, display_name",nativeQuery = true)
	public List<Customer> findByAgeGT(@Param("id") long id);
	
	@Query(value ="select u.* from customer u where u.age<?1 order  by age, display_name",nativeQuery = true)
	public List<Customer> findByAgeLT(@Param("id") long id);
	
	@Query(value ="select u.* from customer u where u.age>=?1 order  by age, display_name",nativeQuery = true)
	public List<Customer> findByAgeGTEq(@Param("id") long id);
	
	@Query(value ="select u.* from customer u where u.age<=?1 order  by age, display_name",nativeQuery = true)
	public List<Customer> findByAgeLTEq(@Param("id") long id);
	
	@Query(value ="select u.* from customer u where u.age=?1 order  by age, display_name",nativeQuery = true)
	public List<Customer> findByAgeEq(@Param("id") long id);

}