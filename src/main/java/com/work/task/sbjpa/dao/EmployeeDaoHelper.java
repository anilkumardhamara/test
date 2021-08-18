package com.work.task.sbjpa.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.work.task.sbjpa.model.EmployeeTest;
import com.work.task.sbjpa.model.Product;

@Repository
public class EmployeeDaoHelper extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	
	public void addEmployee(EmployeeTest emp) {
		String sql = "INSERT INTO employee_test "
				+ "(display_name, first_name, email) VALUES (?, ?,?)";
		getJdbcTemplate().update(sql,
				new Object[] { emp.getDisplayName(), emp.getFirstName(), emp.getEmail() });
	}

	
	public void deleteEmployeeById(String empid) {
		String sql = "DELETE FROM employee_test WHERE employee_id = ?";
		getJdbcTemplate().update(sql, new Object[] { empid });

	}
	
	public void addProduct(Product prd) {
		String sql = "INSERT INTO products "
				+ "(category, pname, price) VALUES (?, ?,?)";
		System.out.println("sql:::"+sql);
		getJdbcTemplate().update(sql,
				new Object[] { prd.getCategory(), prd.getPname(),prd.getPrice() });
	}
}
