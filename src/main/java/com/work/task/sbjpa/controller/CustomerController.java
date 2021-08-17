package com.work.task.sbjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.task.sbjpa.model.Customer;
import com.work.task.sbjpa.repository.*;

@RestController
@RequestMapping("/api/search")
public class CustomerController {
    @Autowired
	private CustomerRepository customerRepository;	
    	 
	 @GetMapping("/customers")
	    public List<Customer> getAllCustomers(){
		  
	     return this.customerRepository.findAllCustomers();
	 
	 }
	 

	    
	    
	    @GetMapping("customer/{id}")
	    public List<Customer> getByReportId(@PathVariable(value = "id") Long id){
	    	
	        System.out.println("getByReportId>>>>"+id);

	        return this.customerRepository.findByCustomerID(id);
	                
	    }
	    
	    @GetMapping("customernames")
	    public List<String> getAllDistinctCustomerName(){
	    	
	        System.out.println("getAllDistinctCustomerName>>>>");

	        return this.customerRepository.findAllDistinctCustomerNames();
	                
	    }
	    
	    
	    @GetMapping("customerAgeGT/{id}")
	    public List<Customer> getByAgeGT(@PathVariable(value = "id") Long id){
	    	
	        System.out.println("getByAgeGT>>>>"+id);

	        return this.customerRepository.findByAgeGT(id);
	                
	    }
	    
	    @GetMapping("customerAgeLT/{id}")
	    public List<Customer> getByAgeLT(@PathVariable(value = "id") Long id){
	    	
	        System.out.println("getByAgeLT>>>>"+id);

	        return this.customerRepository.findByAgeLT(id);
	                
	    }
	    
	    @GetMapping("customerAgeLTEq/{id}")
	    public List<Customer> getByAgeLTEq(@PathVariable(value = "id") Long id){
	    	
	        System.out.println("customerAgeLTEq>>>>"+id);

	        return this.customerRepository.findByAgeLTEq(id);
	                
	    }
	    
	    @GetMapping("customerAgeGTEq/{id}")
	    public List<Customer> getByAgeGTEq(@PathVariable(value = "id") Long id){
	    	
	        System.out.println("customerAgeGTEq>>>>"+id);

	        return this.customerRepository.findByAgeGTEq(id);
	                
	    }
	    
	    @GetMapping("customerAgeEq/{id}")
	    public List<Customer> getByAgeEq(@PathVariable(value = "id") Long id){
	    	
	        System.out.println("customerAgeEq>>>>"+id);

	        return this.customerRepository.findByAgeEq(id);
	                
	    }
	    

}
