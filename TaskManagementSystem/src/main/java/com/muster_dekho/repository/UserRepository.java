package com.muster_dekho.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.muster_dekho.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByUsername(String username);

}
