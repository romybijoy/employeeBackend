package com.java.spring.employeeReg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.employeeReg.entity.Department;
import com.java.spring.employeeReg.entity.Employee;
import com.java.spring.employeeReg.entity.ErrorMessage;
import com.java.spring.employeeReg.error.EmployeeNotFoundException;
import com.java.spring.employeeReg.repository.DepartmentRepository;
import com.java.spring.employeeReg.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	private final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class);
	
	@PostMapping("/employees")
	public Employee saveEmployee(@Valid @RequestBody Employee employee) {

		LOGGER.info("Inside saveEmployee of EmployeeController");
		
		Department department = departmentRepository.findById(employee.getDepartment().getId()).get();
		employee.setDepartment(department);
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployeeList() {
		LOGGER.info("Inside getEmployeeList of EmployeeController");
		return employeeService.getEmployeeList();
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable("id") Long employeeId) throws EmployeeNotFoundException {
		LOGGER.info("Inside getEmployee of EmployeeController");
		return employeeService.getEmployee(employeeId);
	}
	
	@GetMapping("/employees/{employeeNo}/{employeeName}")
	public ResponseEntity<?> getEmployeeByEmployeeNoAndEmployeeName(@PathVariable("employeeNo") int employeeNo,
			@PathVariable("employeeName") String employeeName) {
		LOGGER.info("Inside getEmployeeByEmployeeNoAndEmployeeName of EmployeeController");
		
		
		Employee employee = employeeService.getEmployeeByNoAndName(employeeNo,employeeName);
		
		if(employee == null) {
			ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, "Employee not found");
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@GetMapping("/employees/department/{department}")
	public ResponseEntity<?> getEmployeeByDepartment(@PathVariable("department") Department department) {
		LOGGER.info("Inside getEmployeeByDepartment of EmployeeController");
		
		List<Employee> employee = employeeService.getEmployeeByDepartment(department);
		if(employee.isEmpty()) {
			ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, "Employees not found");
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(employee);
		
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@Valid @RequestBody Employee employee, @PathVariable("id") Long employeeId) throws EmployeeNotFoundException {
		LOGGER.info("Inside updateEmployee of EmployeeController");
		
		Department department = departmentRepository.findById(employee.getDepartment().getId()).get();
		employee.setDepartment(department);
		return employeeService.updateEmployee(employee, employeeId);
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployeeById(@PathVariable("id") Long employeeId) {
		LOGGER.info("Inside deleteEmployeeById of EmployeeController");
		employeeService.deleteEmployeeById(employeeId);
		return "Employee deleted Successfully";
	}
}
