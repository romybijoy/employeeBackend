package com.java.spring.employeeReg.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long employeeId;
	
	@NotNull(message = "Employee number is mandatory")
	@Range(max = 999999999, message = "Maximum length limited to 10")
	private int employeeNo;
	
	@NotBlank(message = "Employee name is mandatory")
	@Length(max = 100)
	private String employeeName;
	
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern =  "dd/MM/yyyy" )
	private LocalDate dateOfJoining;
	
	
	@OneToOne(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "department")
	private Department department;
	
	@NotNull(message = "Salary is mandatory")
	@Range(max = 999999999, message = "Maximum salary amount length limited to 10")
	private int salary;
	
	

}
