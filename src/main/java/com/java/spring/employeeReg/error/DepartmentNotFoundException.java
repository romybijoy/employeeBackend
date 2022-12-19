package com.java.spring.employeeReg.error;

public class DepartmentNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepartmentNotFoundException() {
		super();
	}
	public DepartmentNotFoundException(String message) {
		super(message);
	}

	public DepartmentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DepartmentNotFoundException(Throwable cause) {
		super(cause);
	}
}
