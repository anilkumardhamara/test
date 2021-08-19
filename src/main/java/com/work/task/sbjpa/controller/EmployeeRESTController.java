package com.work.task.sbjpa.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.task.sbjpa.dto.EmployeeDto;
import com.work.task.sbjpa.dto.Response;
import com.work.task.sbjpa.exception.RecordNotInsertedException;
import com.work.task.sbjpa.model.*;
import com.work.task.sbjpa.repository.EmployeeRepository;
import com.work.task.sbjpa.service.EmployeeServiceTest;
import com.work.task.sbjpa.service.OrganizationService;
import com.work.task.sbjpa.service.impl.InvalidInsuranceAmountException;
import com.work.task.sbjpa.util.Constants;


@RestController
@RequestMapping("/api")
public class EmployeeRESTController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeServiceTest employeeServiceTest;
	
	@Autowired
	private OrganizationService organizationService ;

	String methodName = "";
	static final String CLASS_NAME = "EmployeeRESTController";
	Logger logger = LoggerFactory.getLogger(EmployeeRESTController.class);

	
  @PostMapping(value = "/employees") 
  public  ResponseEntity<Response>  addEmployee (@RequestBody EmployeeDto employee) {
	  System.out.println("addEmployee::::"+employee.getDisplayName());
	 try {
			if(employee.getFirstName().length()<4||employee.getLastName().length()<4||employee.getDisplayName().length()<4) {

				return new ResponseEntity<>(new Response("Please Fill the Name Fields  atleast 4 Char", Constants.FALSE, ""),
							HttpStatus.EXPECTATION_FAILED);
			}
			if(!(employee.getEmail().contains("@")&&employee.getEmail().contains(".com"))){
				  return new ResponseEntity<>(new Response("Mail shoud be in xxxxxx@xxx.com format", Constants.FALSE, employee.getEmail()),
						HttpStatus.EXPECTATION_FAILED);	
			}

	  EmployeeTest emp = employeeServiceTest.addEmployee(employee); 
	  
	  return new ResponseEntity<>(new Response("EmployeeTest Details Saved", Constants.TRUE, emp),
				HttpStatus.CREATED);
	 }catch(Exception e) {
			return new ResponseEntity<>(new Response("EmployeeTest Details not Saved"+e.getMessage(), Constants.FALSE, ""),
					HttpStatus.NOT_MODIFIED);		 
	 }
  }
	 

	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<Response> getEmployeeById(@PathVariable("id") int id) {

		try {
			methodName = "getEmployeeById";
			logger.info(CLASS_NAME + " :: " + methodName + " :: " + "started");
			EmployeeTest employee = employeeRepository.getEmployeeById(id);
			logger.info(CLASS_NAME + " :: " + methodName + " :: " + " Ended");
			if (employee != null) {
				return new ResponseEntity<>(new Response("Data Found", Constants.TRUE, employee), HttpStatus.ACCEPTED);
			} 
				throw new Exception("Not Found");
			
		} catch (Exception e) {
			return new ResponseEntity<>(new Response("EmployeeTest Details not found", Constants.FALSE, ""),
					HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(value = "/employeesName/{name}")
	public ResponseEntity<Response> getEmployeeByName(@PathVariable("name") String name) {

		try {
			methodName = "getEmployeeById";
			logger.info(CLASS_NAME + " :: " + methodName + " :: " + "started");
			EmployeeTest employee = employeeRepository.getEmployeeByName(name);
			logger.info(CLASS_NAME + " :: " + methodName + " :: " + " Ended"+employee.getDisplayName().charAt(0));

			return new ResponseEntity<>(new Response("EmployeeTest Details found ", Constants.TRUE, employee),
						HttpStatus.FOUND);

		} catch (NullPointerException  e) {
			return new ResponseEntity<>(new Response("EmployeeTest not found", Constants.FALSE, e.getCause()),
					HttpStatus.NOT_FOUND);
		}

	}
	
 
    
    @PostMapping(value = "/employeesbytrn") 
    public  ResponseEntity<Response>  addEmployeeTrn (@RequestBody EmployeeDto employee) {
    	
    	try {
    		   employeeServiceTest.addEmployeeByTransaction(employee); 
    		   if(employee.getDisplayName().equalsIgnoreCase("test"))
    			   throw new Exception("Not Found");
    		  
    		  return new ResponseEntity<>(new Response("EmployeeTest Details Saved", Constants.TRUE, ""),
    					HttpStatus.CREATED);
    		 }catch(Exception e) {
    				return new ResponseEntity<>(new Response("EmployeeTest Details not Saved"+e.getMessage(), Constants.FALSE, ""),
    						HttpStatus.NOT_MODIFIED);		 
    		 }
    	
    }
    
    
    @PostMapping(value = "/empTrn") 
    public  ResponseEntity<Response>  trnPropagation (@RequestBody EmployeeDto employee) throws InvalidInsuranceAmountException{

		Employee emp = new Employee();
		emp.setEmpId(employee.getEmpId());
		emp.setEmpName(employee.getEmpName());

		EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();
		employeeHealthInsurance.setEmpId(employee.getEmpId());
		employeeHealthInsurance.setHealthInsuranceSchemeName(employee.getHealthInsuranceSchemeName());
		employeeHealthInsurance.setCoverageAmount(employee.getCoverageAmount());
		String propagation = employee.getPropagation();
		if(employee.getPropagation().isEmpty()) propagation="Null";

		organizationService.joinOrganization(emp, employeeHealthInsurance, propagation);
		
		 return new ResponseEntity<>(new Response("EmployeeTest Details Saved", Constants.TRUE, ""),
					HttpStatus.CREATED);

	}
    
    
}