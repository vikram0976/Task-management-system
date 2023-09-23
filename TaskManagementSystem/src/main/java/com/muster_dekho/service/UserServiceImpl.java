package com.muster_dekho.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muster_dekho.dto.UserDto;
import com.muster_dekho.exception.UserException;
import com.muster_dekho.exception.UserFoundException;
import com.muster_dekho.model.CurrentLoginSession;
import com.muster_dekho.model.LoginDto;
import com.muster_dekho.model.User;
import com.muster_dekho.repository.CurrentUserSessionRepo;
import com.muster_dekho.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private CurrentUserSessionRepo curretnUserReporStary;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public UserDto registerUser(User user) throws Exception {
		
		List<User> list = userRepo.findByUsername(user.getUsername());
		
		if(!list.isEmpty())
			throw new UserException("Username \""+user.getUsername()+"\" is already registered");
		
		user.setTasks(new ArrayList<>());
		
		User saved = userRepo.save(user);
		
		UserDto dto = new UserDto();
		
		dto.setId(saved.getId());
		dto.setMobile(saved.getMobile());
		dto.setName(saved.getName());
		dto.setTasks(saved.getTasks());
		dto.setUsername(saved.getUsername());
		
		return dto;
		
	}
	
	@Override
	public CurrentLoginSession accessUserAccount(LoginDto dto) throws Exception {
		List<User> list=userRepo.findByUsername(dto.getUsername());
		
		if(list.size()==0) {
			throw new LoginException("please enter valid username");
		}
		
		User user=list.get(0);
		Optional<CurrentLoginSession> validation=curretnUserReporStary.findById(user.getId());
		
		if(validation.isPresent()) {
			if(user.getPassword().equals(dto.getPassword())) {
				return validation.get();
			}
			throw new LoginException("Please enter valid password");
		}
		
		if(user.getPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentLoginSession cus=new CurrentLoginSession();
			cus.setUserId(user.getId());
			cus.setUuid(key);
			cus.setLocalDateTime(LocalDateTime.now());
			curretnUserReporStary.save(cus);
			return cus;
		}
		
		throw new LoginException("please enter valid password");
	}

	@Override
	public String endUserSession(String key) throws Exception {
		List<CurrentLoginSession> validation=curretnUserReporStary.findByUuid(key);
		if(validation.size()==0) {
			throw new LoginException("user not logged in with this username");
		}
		curretnUserReporStary.delete(validation.get(0));
		return "Logged out !";
	}

	@Override
	public UserDto fetchUserById(Long userId) throws UserFoundException {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserFoundException(userId));
		
		UserDto dto = new UserDto();
		
		dto.setId(user.getId());
		dto.setMobile(user.getMobile());
		dto.setName(user.getName());
		dto.setTasks(user.getTasks());
		dto.setUsername(user.getUsername());
		
		return dto;
		
	}

	

}


class RandomString {
	 
	static String make(int n){
	 
		String AlphaNumStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	 
		StringBuilder sb = new StringBuilder(n);
	 
		for (int i = 0; i < n; i++) {
	 
			int index = (int)(AlphaNumStr.length() * Math.random());
	 
			sb.append(AlphaNumStr.charAt(index));
		}
	 
		return sb.toString();
	}

}
