package com.java.spring.employeeReg.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.java.spring.employeeReg.controller.EmployeeController;
import com.java.spring.employeeReg.entity.Department;
import com.java.spring.employeeReg.error.DepartmentNotFoundException;
import com.java.spring.employeeReg.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	private final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class);
	
	@Override
	public List<Department> getDepartmentList() {
		LOGGER.info("Inside getDepartmentList of DepartmentService");
		return departmentRepository.findAll();
	}


	@Override
	public Department getDepartment(Long id) throws DepartmentNotFoundException {
		LOGGER.info("Inside getDepartment of DepartmentService");
		
		Optional<Department> department = departmentRepository.findById(id);
		
		if(!department.isPresent()) {
			throw new DepartmentNotFoundException("Department not available");
		}
		
		return department.get();
		}

	@Override
	public Department saveDepartment(Department department) {
		LOGGER.info("Inside saveDepartment of DepartmentService");
		return departmentRepository.save(department);
	}

	@Override
	public Department updateDepartment(Department department, Long id) throws DepartmentNotFoundException {
		
		LOGGER.info("Inside updateDepartment of DepartmentService");
		
         Optional<Department> depar = departmentRepository.findById(id);
		
		if(!depar.isPresent()) {
			throw new DepartmentNotFoundException("Department not available");
		}
		
		Department depDB = depar.get();
		
		if(Objects.nonNull(depDB.getCode()) && !"".equalsIgnoreCase(depDB.getCode())) {
			depDB.setCode(department.getCode());
		}
		if(Objects.nonNull(depDB.getDescription()) && !"".equalsIgnoreCase(depDB.getDescription())) {
			depDB.setDescription(department.getDescription());
		}
		return departmentRepository.save(depDB);
	}


	@Override
	public void deleteDepartmentById(Long id) throws EmptyResultDataAccessException {
		LOGGER.info("Inside deleteDepartmentById of DepartmentService");
		departmentRepository.deleteById(id); 
	}

}
