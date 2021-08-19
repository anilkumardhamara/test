package com.work.task.sbjpa;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.task.sbjpa.controller.EmployeeRESTController;
import com.work.task.sbjpa.dto.EmployeeDto;
import com.work.task.sbjpa.model.Employee;
import com.work.task.sbjpa.model.EmployeeTest;
import com.work.task.sbjpa.repository.EmployeeRepository;
import com.work.task.sbjpa.service.EmployeeServiceTest;
import com.work.task.sbjpa.service.OrganizationService;



@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeRESTController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = EmployeeRESTController.class)
public class EmployeeRESTControllertest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	@MockBean
	private EmployeeServiceTest employeeServiceTest;
	
	@MockBean
	private OrganizationService organizationService ;



	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	public void getEmployeId() throws Exception {
		EmployeeTest  emp =new EmployeeTest();
		emp.setEmployeeId(1);
		when(employeeRepository.getEmployeeById(1)).thenReturn(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.get("/api/employees/1");
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();
		System.out.println(">>>>>>>>>>>>>>>>>>>"+status);
		assertEquals(202, status);
	}
	
	@Test
	public void getEmployeIdCatch() throws Exception {
		EmployeeTest  emp =new EmployeeTest();
		
		emp.setEmployeeId(12);
		when(employeeRepository.getEmployeeById(152)).thenThrow();
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.get("/api/employees/134");
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();
		System.out.println(">>>>>>>>>>>>>>>>>>>"+status);
		assertEquals(404, status);
	}
	
	@Test
	public void getEmployeName() throws Exception {

		EmployeeTest  emp =new EmployeeTest();
		emp.setDisplayName("pk");
		when(employeeRepository.getEmployeeByName("pk")).thenReturn(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.get("/api/employeesName/pk");
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();
		System.out.println(">>>>>>>>>>>>>>>>>>>"+status);
		assertEquals(302, status);
	}
	
	@Test
	public void getEmployeeNameCatch() throws Exception {

		EmployeeTest  emp =new EmployeeTest();
		emp.setDisplayName(null);
		when(employeeRepository.getEmployeeByName("none")).thenReturn(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.get("/api/employeesName/none");
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();
		System.out.println(">>>>>>>>>>>>>>>>>>>"+status);
		assertEquals(404, status);
	}

	@Test
	public void addEmployee() throws Exception {

		EmployeeDto  emp =new EmployeeDto();
		emp.setDisplayName("John Smith");
		emp.setFirstName("Smith");
		emp.setLastName("John");
		emp.setEmail("john@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String jsonModel = mapper.writeValueAsString(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.post("/api/employees")
				.accept(MediaType.APPLICATION_JSON).content(jsonModel).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(201, response.getStatus());
	}
	
	@Test
	public void addEmployeeNameValidation() throws Exception {

		EmployeeDto  emp =new EmployeeDto();
		emp.setDisplayName("John Smith");
		emp.setFirstName("Sm");
		emp.setLastName("John");
		emp.setEmail("john@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String jsonModel = mapper.writeValueAsString(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.post("/api/employees")
				.accept(MediaType.APPLICATION_JSON).content(jsonModel).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(417, response.getStatus());
	}
	
	@Test
	public void addEmployeeMailValidation() throws Exception {

		EmployeeDto  emp =new EmployeeDto();
		emp.setDisplayName("John Smith");
		emp.setFirstName("Smith");
		emp.setLastName("John");
		emp.setEmail("john@gmail");
		ObjectMapper mapper = new ObjectMapper();
		String jsonModel = mapper.writeValueAsString(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.post("/api/employees")
				.accept(MediaType.APPLICATION_JSON).content(jsonModel).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(417, response.getStatus());
	}
	
	@Test
	public void addEmployeeTrn() throws Exception {

		EmployeeDto  emp =new EmployeeDto();
		emp.setDisplayName("John Smith");
		emp.setFirstName("Smith");
		emp.setEmail("john@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String jsonModel = mapper.writeValueAsString(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.post("/api/employeesbytrn")
				.accept(MediaType.APPLICATION_JSON).content(jsonModel).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(201, response.getStatus());
	}
	
	@Test
	public void addEmployeeTrnCatch() throws Exception {

		EmployeeTest emp =new EmployeeTest();
		emp.setDisplayName("test");
		emp.setFirstName("Smith");
		emp.setEmail("john@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String jsonModel = mapper.writeValueAsString(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.post("/api/employeesbytrn")
				.accept(MediaType.APPLICATION_JSON).content(jsonModel).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(304, response.getStatus());
	}
	
	@Test
	public void trnPropagation() throws Exception {

		EmployeeDto emp =new EmployeeDto();
		emp.setEmpId("1");
		emp.setEmpName("Smith");
		emp.setEmail("john@gmail.com");
		emp.setHealthInsuranceSchemeName("Star");
		emp.setCoverageAmount(1200);
		emp.setPropagation("REQUIRED");
		ObjectMapper mapper = new ObjectMapper();
		String jsonModel = mapper.writeValueAsString(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.post("/api/empTrn")
				.accept(MediaType.APPLICATION_JSON).content(jsonModel).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(201, response.getStatus());
	}
	
	@Test
	public void trnPropagationNever() throws Exception {

		EmployeeDto emp =new EmployeeDto();
		emp.setEmpId("1");
		emp.setEmpName("Smith");
		emp.setEmail("john@gmail.com");
		emp.setHealthInsuranceSchemeName("Star");
		emp.setCoverageAmount(1200);
		emp.setPropagation("NEVER");
		ObjectMapper mapper = new ObjectMapper();
		String jsonModel = mapper.writeValueAsString(emp);
		MockHttpServletRequestBuilder getReq = MockMvcRequestBuilders.post("/api/empTrn")
				.accept(MediaType.APPLICATION_JSON).content(jsonModel).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(getReq).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(201, response.getStatus());
	}
	

}


