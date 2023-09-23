package com.muster_dekho.service;

import java.time.LocalDate;
import java.util.List;

import com.muster_dekho.dto.TaskDto;
import com.muster_dekho.model.Task;

public interface TaskService {
	/**
	 * Create a new task.
	 */
	public TaskDto createTask(String token, Task task) throws Exception;

	/**
	 * Update an existing task.
	 */
	public TaskDto updateTask(String token, Task updatedTask) throws Exception;

	/**
	 * Assign a task to another user.
	 */
	public TaskDto assignTaskToAnotherUser(String token, Long taskId, Long userId) throws Exception;

	/**
	 * Mark a task as complete or incomplete.
	 */
	public TaskDto markTaskComplete(String token, Long taskId) throws Exception;

	/**
	 * Delete a task.
	 */
	public Task deleteTask(String token, Long taskId) throws Exception;

	/**
	 * Get a task by ID.
	 */
	public TaskDto getTaskById(Long taskId) throws Exception;

	/**
	 * Search tasks by title.
	 */
	public List<TaskDto> searchTaskByTitle(String token, String title) throws Exception;

	/**
	 * Search tasks by description.
	 */
	public List<TaskDto> searchTaskByDescription(String token, String description) throws Exception;

	/**
	 * Retrieve tasks assigned to a user.
	 */
	public List<TaskDto> searchTaskOfUser(Long userId) throws Exception;

	/**
	 * Filter tasks by completion status.
	 */
	public List<TaskDto> filterTaskByCompletionStatus(String token, Boolean completedStatus) throws Exception;

	/**
	 * Filter tasks by due date.
	 */
	public List<TaskDto> filterTaskByDueDate(String token, LocalDate dueDate) throws Exception;

	/**
	 * Filter tasks by completion status and due date.
	 */
	public List<TaskDto> filterTaskByCompletionStatusAndDueDate(String token, Boolean completedStatus, LocalDate dueDate) throws Exception;

    
}
