package com.java.spring.employeeReg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.spring.employeeReg.entity.Department;
import com.java.spring.employeeReg.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	public Employee findByEmployeeNoAndEmployeeName(int employeeNo, String employeeName);

	public List<Employee> findByDepartment(Department department);
}
