package com.muster_dekho.service;

import com.muster_dekho.dto.UserDto;
import com.muster_dekho.model.CurrentLoginSession;
import com.muster_dekho.model.LoginDto;
import com.muster_dekho.model.User;

public interface UserService {

	/**
	 * Add a new user to the system.
	
	 */
	public UserDto registerUser(User user)  throws Exception ;

	/**
	 * Log into the user account using the provided login credentials.
	 *
	 */
	public CurrentLoginSession accessUserAccount(LoginDto dto)throws Exception;
	
	/**
	 * Log out from the user account using the provided authentication token.
	 */
	public String endUserSession(String token)throws Exception;

	/**
	 * Get the user by provided user ID.
	 */
	public UserDto fetchUserById(Long userId)throws Exception ;



	
	

}
