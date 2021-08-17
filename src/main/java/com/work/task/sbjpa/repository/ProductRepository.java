package com.work.task.sbjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.task.sbjpa.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
	@Query(value ="select u.* from products u where u.id=?1",nativeQuery = true)
	public List<Product> findByProductID(@Param("id") long id);
	
	
	@Query(value ="select distinct u.pname from products u ",nativeQuery = true)
	public List<String> findAllDistinctProductNames();

}