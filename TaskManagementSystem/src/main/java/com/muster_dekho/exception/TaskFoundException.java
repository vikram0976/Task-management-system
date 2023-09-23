package com.muster_dekho.exception;

public class TaskFoundException extends Exception{

	public TaskFoundException() {
		super("No Task Found");
	}

	public TaskFoundException(Long id) {
		super("Task Not Found With Id "+id);
	}
	
	

}
