package com.muster_dekho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.muster_dekho.model.CurrentLoginSession;

@Repository
public interface CurrentUserSessionRepo extends JpaRepository<CurrentLoginSession, Long>{
	
	public List<CurrentLoginSession> findByUuid(String uuid);
	
}
