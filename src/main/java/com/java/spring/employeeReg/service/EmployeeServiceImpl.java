package com.java.spring.employeeReg.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.spring.employeeReg.controller.EmployeeController;
import com.java.spring.employeeReg.entity.Department;
import com.java.spring.employeeReg.entity.Employee;
import com.java.spring.employeeReg.error.EmployeeNotFoundException;
import com.java.spring.employeeReg.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class);
	
	@Override
	public List<Employee> getEmployeeList() {
		LOGGER.info("Inside getEmployeeList of EmployeeService");
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployee(Long id) throws EmployeeNotFoundException {
		LOGGER.info("Inside getEmployee of EmployeeService");
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee not available");
		}
		
		return employee.get();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		LOGGER.info("Inside saveEmployee of EmployeeService");
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee, Long id) throws EmployeeNotFoundException {
		
		LOGGER.info("Inside updateEmployee of EmployeeService");
		
		
         Optional<Employee> emp = employeeRepository.findById(id);
		
		if(!emp.isPresent()) {
			throw new EmployeeNotFoundException("Employee not available");
		}
		
		Employee employeeDB = emp.get();
		
		if(Objects.nonNull(employeeDB.getEmployeeName()) && !"".equalsIgnoreCase(employeeDB.getEmployeeName())) {
			employeeDB.setEmployeeName(employee.getEmployeeName());
		}
		if(Objects.nonNull(employeeDB.getEmployeeNo())) {
			employeeDB.setEmployeeNo(employee.getEmployeeNo());
		}
		if(Objects.nonNull(employeeDB.getDateOfJoining())) {
			employeeDB.setDateOfJoining(employee.getDateOfJoining());
		}
		if(Objects.nonNull(employeeDB.getDepartment())) {
			employeeDB.setDepartment(employee.getDepartment());
		}
		if(Objects.nonNull(employeeDB.getSalary())) {
			employeeDB.setSalary(employee.getSalary());
		}
		return employeeRepository.save(employeeDB);
	}


	@Override
	public void deleteEmployeeById(Long id) {
		LOGGER.info("Inside deleteEmployeeById of EmployeeService");
		employeeRepository.deleteById(id); 
	}


	@Override
	public Employee getEmployeeByNoAndName(int employeeNo, String employeeName) {
		LOGGER.info("Inside getEmployeeByNoAndName of EmployeeService");
		return employeeRepository.findByEmployeeNoAndEmployeeName(employeeNo,employeeName);
	}
	
	@Override
	public List<Employee> getEmployeeByDepartment(Department department) {
		LOGGER.info("Inside getEmployeeByDepartment of EmployeeService");
		
		return employeeRepository.findByDepartment(department);
	}
}
