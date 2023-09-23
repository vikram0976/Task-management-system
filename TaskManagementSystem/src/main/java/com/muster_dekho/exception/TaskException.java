package com.muster_dekho.exception;

public class TaskException extends Exception{

	public TaskException() {
		super("No Task Found");
	}

	public TaskException(String message) {
		super(message);
	}
	
	

}
