package com.bridgelabz.employeepayrollapp.exception;

import java.util.List;
import java.util.stream.Collectors;

import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class EmployeePayrollExceptionHandler {
	private static final String message = "Exception while processing REST Request";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errMesg = errorList.stream().map(objerr -> objerr.getDefaultMessage())
				.collect(Collectors.toList());

		ResponseDTO responseDTO = new ResponseDTO(message, errMesg);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmployeePayrollException.class)
	public ResponseEntity<ResponseDTO> handleEmployeePayrollException(EmployeePayrollException exception) {
		ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST request", exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseDTO> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException exception) {
		log.error("Invalid Date Format", exception);
		ResponseDTO responseDTO = new ResponseDTO(message, "Should have date in the format dd MMM yyyy");
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);

	}
}