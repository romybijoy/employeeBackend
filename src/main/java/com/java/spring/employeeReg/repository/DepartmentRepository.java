package com.java.spring.employeeReg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.spring.employeeReg.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
