package com.muster_dekho.exception;

public class UserException extends Exception{

	public UserException() {
		super("No User Found");
	}

	public UserException(String message) {
		super(message);
	}
	
	

}
