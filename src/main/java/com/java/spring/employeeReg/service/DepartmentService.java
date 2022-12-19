package com.java.spring.employeeReg.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.java.spring.employeeReg.entity.Department;
import com.java.spring.employeeReg.error.DepartmentNotFoundException;

@Service
public interface DepartmentService {

	public List<Department> getDepartmentList();

	public Department getDepartment(Long id) throws DepartmentNotFoundException;
	
	public Department saveDepartment(Department department);

	public Department updateDepartment(Department department, Long id) throws DepartmentNotFoundException;

	public void deleteDepartmentById(Long id) throws EmptyResultDataAccessException;

}
