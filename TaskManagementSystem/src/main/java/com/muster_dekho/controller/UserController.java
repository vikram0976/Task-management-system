package com.muster_dekho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muster_dekho.dto.UserDto;
import com.muster_dekho.model.CurrentLoginSession;
import com.muster_dekho.model.LoginDto;
import com.muster_dekho.model.User;
import com.muster_dekho.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * Welcome endpoint.
	 */
	@GetMapping("/welcome")
	public ResponseEntity<String> welcome() {
	    return new ResponseEntity<>("Welcome", HttpStatus.OK);
	}

	/**
	 * Register a new user.
	 */
	@PostMapping
	public ResponseEntity<UserDto> registerUser(@RequestBody @Valid User user) throws Exception {
	    return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
	}

	/**
	 * Login a user.
	 */
	@PostMapping("/login")
	public ResponseEntity<CurrentLoginSession> loginUser(@RequestBody LoginDto dto) throws Exception {
	    return new ResponseEntity<>(userService.accessUserAccount(dto), HttpStatus.OK);
	}

	/**
	 * Logout a user.
	 */
	@PostMapping("/logout/{token}")
	public ResponseEntity<String> logoutUser(@PathVariable("token") String token) throws Exception {
	    return new ResponseEntity<>(userService.endUserSession(token), HttpStatus.OK);
	}

	/**
	 * Get user details by ID.
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws Exception {
	    return new ResponseEntity<>(userService.fetchUserById(userId), HttpStatus.OK);
	}

    
}
