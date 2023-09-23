package com.muster_dekho.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	

	
	@ExceptionHandler(TaskFoundException.class)
	public ResponseEntity<ExceptionDto> taskNotFoundExceptionHandler(TaskFoundException e, WebRequest wr){
		
		ExceptionDto err=new ExceptionDto();
		err.setDateAndTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDesc(wr.getDescription(false));
		
		return new ResponseEntity<ExceptionDto>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<ExceptionDto> userNotFoundExceptionHandler(UserFoundException e, WebRequest wr){
		
		ExceptionDto err=new ExceptionDto();
		err.setDateAndTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDesc(wr.getDescription(false));
		
		return new ResponseEntity<ExceptionDto>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	
//	==========================================================================
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDto> validationExceptionHandler(MethodArgumentNotValidException e){
		
		ExceptionDto err=new ExceptionDto();
		err.setDateAndTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDesc(e.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<ExceptionDto>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ExceptionDto> nullPointerExceptionHandler(NullPointerException e, WebRequest wr){
		
		ExceptionDto err=new ExceptionDto();
		err.setDateAndTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDesc(wr.getDescription(false));
		
		return new ResponseEntity<ExceptionDto>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ExceptionDto> noHandlerFoundException(NoHandlerFoundException e, WebRequest wr){
		
		ExceptionDto err=new ExceptionDto();
		err.setDateAndTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDesc(wr.getDescription(false));
		
		return new ResponseEntity<ExceptionDto>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDto> masterExceptionHandler(Exception e, WebRequest wr){
		
		ExceptionDto err=new ExceptionDto();
		err.setDateAndTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDesc(wr.getDescription(false));
		
		return new ResponseEntity<ExceptionDto>(err, HttpStatus.BAD_REQUEST);
		
	}

}
