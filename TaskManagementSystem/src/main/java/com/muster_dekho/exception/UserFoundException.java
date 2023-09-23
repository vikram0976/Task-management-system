package com.muster_dekho.exception;

public class UserFoundException extends Exception{

	public UserFoundException() {
		super("No User Found");
	}

	public UserFoundException(Long id) {
		super("User Not Found With Id "+id);
	}
	
	

}
