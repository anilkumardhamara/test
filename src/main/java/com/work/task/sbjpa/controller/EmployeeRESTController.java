package com.work.task.sbjpa.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.work.task.sbjpa.model.Employee;
import com.work.task.sbjpa.repository.EmployeeRepository;
import com.work.task.sbjpa.service.EmployeeService;
import com.work.task.sbjpa.util.Constants;

@RestController
@RequestMapping("/api")
public class EmployeeRESTController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeService employeeService;
	String methodName = "";
	static final String CLASS_NAME = "EmployeeRESTController";
	Logger logger = LoggerFactory.getLogger(EmployeeRESTController.class);

	
  @PostMapping(value = "/employees") 
  public  ResponseEntity<Response>  addEmployee (@RequestBody EmployeeDto employee) {
	  System.out.println("addEmployee::::"+employee.getDisplayName());
	 try {
			if(employee.getFirstName().length()<4) {

				return new ResponseEntity<>(new Response("Please Fill the Firstname  atleast 4 Char", Constants.FALSE, employee.getFirstName()),
							HttpStatus.EXPECTATION_FAILED);
			}
			if(employee.getLastName().length()<4){

				  return new ResponseEntity<>(new Response("Please Fill the Lastname  atleast 4 Char", Constants.FALSE, employee.getLastName()),
						HttpStatus.EXPECTATION_FAILED);	
			}
			if(employee.getDisplayName().length()<8){

				  return new ResponseEntity<>(new Response("Please Fill the DisplayName  atleast 8 Char", Constants.FALSE, employee.getDisplayName()),
						HttpStatus.EXPECTATION_FAILED);	
			}
			if(!(employee.getEmail().contains("@")&&employee.getEmail().contains(".com"))){
				  return new ResponseEntity<>(new Response("Mail shoud be in xxxxxx@xxx.com format", Constants.FALSE, employee.getEmail()),
						HttpStatus.EXPECTATION_FAILED);	
			}

	  Employee emp = employeeService.addEmployee(employee); 
	  
	  return new ResponseEntity<>(new Response("Employee Details Saved", Constants.TRUE, emp),
				HttpStatus.CREATED);
	 }catch(Exception e) {
			return new ResponseEntity<>(new Response("Employee Details not Saved"+e.getMessage(), Constants.FALSE, ""),
					HttpStatus.NOT_MODIFIED);		 
	 }
  }
	 

	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<Response> getEmployeeById(@PathVariable("id") int id) {

		try {
			methodName = "getEmployeeById";
			logger.info(CLASS_NAME + " :: " + methodName + " :: " + "started");
			Employee employee = employeeRepository.getEmployeeById(id);
			logger.info(CLASS_NAME + " :: " + methodName + " :: " + " Ended");
			if (employee != null) {
				return new ResponseEntity<>(new Response("Data Found", Constants.TRUE, employee), HttpStatus.FOUND);
			} else {
				return new ResponseEntity<>(new Response("Employee Details not found", Constants.FALSE, ""),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new Response("Employee Details not found", Constants.FALSE, ""),
					HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(value = "/employeesName/{name}")
	public ResponseEntity<Response> getEmployeeByName(@PathVariable("name") String name) {

		try {
			methodName = "getEmployeeById";
			logger.info(CLASS_NAME + " :: " + methodName + " :: " + "started");
			Employee employee = employeeRepository.getEmployeeByName(name);
			logger.info(CLASS_NAME + " :: " + methodName + " :: " + " Ended"+employee.getDisplayName().charAt(0));

			return new ResponseEntity<>(new Response("Employee Details found ", Constants.TRUE, employee),
						HttpStatus.FOUND);

		} catch (NullPointerException  e) {
			return new ResponseEntity<>(new Response("Employee not found", Constants.FALSE, e.getCause()),
					HttpStatus.NOT_FOUND);
		}

	}
	
    @DeleteMapping("deleteemployee/{id}")
    public ResponseEntity<Response> removeUser(@PathVariable(value = "id") Long id){
    	try {
    		System.out.println("removeUser>>>>"+id);
    	 Employee user =this.employeeRepository.findById(id).orElseThrow();
       
        this.employeeRepository.delete(user);
        return new ResponseEntity<>(new Response("Employee Record Deleted ", Constants.TRUE, id),
				HttpStatus.FOUND);
    } catch (NullPointerException  e) {
		return new ResponseEntity<>(new Response("Employee Record not found", Constants.FALSE, e.getCause()),
				HttpStatus.NOT_FOUND);
	}

    }
    
}