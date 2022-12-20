package com.java.spring.employeeReg.error;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.java.spring.employeeReg.entity.ErrorMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestControllerAdvice
public class MethodExceptionHandler {

	private final Logger LOGGER = LoggerFactory.getLogger(MethodExceptionHandler.class);

	ErrorMessage message;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleInvalidArgument(MethodArgumentNotValidException ex) {
		
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            // errorMap.put(error.getField(), error.getDefaultMessage());
			message = new ErrorMessage(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}