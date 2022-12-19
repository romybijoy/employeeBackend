package com.java.spring.employeeReg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.spring.employeeReg.entity.Department;
import com.java.spring.employeeReg.entity.Employee;
import com.java.spring.employeeReg.error.EmployeeNotFoundException;

@Service
public interface EmployeeService {

	public List<Employee> getEmployeeList();

	public Employee getEmployee(Long id) throws EmployeeNotFoundException;
	
	public Employee saveEmployee(Employee employee);

	public Employee updateEmployee(Employee employee, Long id) throws EmployeeNotFoundException;

	public void deleteEmployeeById(Long id);

	public Employee getEmployeeByNoAndName(int employeeNo, String employeeName);

	public List<Employee> getEmployeeByDepartment(Department department);


}
