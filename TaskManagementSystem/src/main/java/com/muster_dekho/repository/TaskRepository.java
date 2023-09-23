package com.muster_dekho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muster_dekho.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	List<Task> findByAssignedUserIdAndTitleContainingIgnoreCase(Long id, String title);
	
	List<Task> findByAssignedUserIdAndDescriptionContainingIgnoreCase(Long id, String description);

}
