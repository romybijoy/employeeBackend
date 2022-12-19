package com.java.spring.employeeReg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.employeeReg.entity.Department;
import com.java.spring.employeeReg.error.DepartmentNotFoundException;
import com.java.spring.employeeReg.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	private final Logger LOGGER= LoggerFactory.getLogger(DepartmentController.class);
	
	@PostMapping("/departments")
	public Department saveDepartment(@Valid @RequestBody Department department) {

		LOGGER.info("Inside saveDepartment of DepartmentController");
		return departmentService.saveDepartment(department);
	}
	
	@GetMapping("/departments")
	public List<Department> getDepartmentList() {
		LOGGER.info("Inside getDepartmentList of DepartmentController");
		return departmentService.getDepartmentList();
	}
	
	@GetMapping("/departments/{id}")
	public Department getDepartment(@PathVariable("id") Long id) throws DepartmentNotFoundException {
		LOGGER.info("Inside getDepartment of DepartmentController");
		return departmentService.getDepartment(id);
	}

	@PutMapping("/departments/{id}")
	public Department updateDepartment(@Valid @RequestBody Department department, @PathVariable("id") Long id) throws DepartmentNotFoundException {
		LOGGER.info("Inside updateDepartment of DepartmentController");
		return departmentService.updateDepartment(department, id);
	}
	
	@DeleteMapping("/departments/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long id) throws EmptyResultDataAccessException {
		LOGGER.info("Inside deleteDepartmentById of DepartmentController");
		departmentService.deleteDepartmentById(id);
		return "Department deleted Successfully";
	}
}
