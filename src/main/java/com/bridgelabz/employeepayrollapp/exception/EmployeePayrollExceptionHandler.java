package com.bridgelabz.employeepayrollapp.exception;

import java.util.List;
import java.util.stream.Collectors;

import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeePayrollExceptionHandler 
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
        List<String> errMesg=errorList.stream()
                             .map(objerr->objerr.getDefaultMessage())
                             .collect(Collectors.toList());

        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST request", errMesg);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
    }
}