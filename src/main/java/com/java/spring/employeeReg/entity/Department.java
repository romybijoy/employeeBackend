package com.java.spring.employeeReg.entity;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@NotBlank(message = "Department code is mandatory")
	@Length(max = 2, message = "Code length must be 2 character long")
	private String code;
	
	@NotBlank(message = "Department name is mandatory")
	private String description;
	
}
