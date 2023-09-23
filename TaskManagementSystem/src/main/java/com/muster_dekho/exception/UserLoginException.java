package com.muster_dekho.exception;

public class UserLoginException extends Exception{

	public UserLoginException() {
		super("token expired please log in again");
	}

	public UserLoginException(String message) {
		super(message);
	}
	
	

}
