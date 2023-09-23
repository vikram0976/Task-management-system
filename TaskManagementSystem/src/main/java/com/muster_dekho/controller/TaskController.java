package com.muster_dekho.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muster_dekho.dto.TaskDto;
import com.muster_dekho.model.Task;
import com.muster_dekho.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	/**
	 * Welcome endpoint.
	 */
	@GetMapping("/tasks/welcome")
	public ResponseEntity<String> welcome() {
	    return new ResponseEntity<>("Welcome", HttpStatus.OK);
	}

	/**
	 * Create a new task.
	 */
	@PostMapping("/tasks/{token}")
	public ResponseEntity<TaskDto> createTask(@PathVariable String token, @RequestBody @Valid Task task) throws Exception {
	    return new ResponseEntity<>(taskService.createTask(token, task), HttpStatus.OK);
	}

	/**
	 * Update an existing task.
	 */
	@PutMapping("/tasks/{token}")
	public ResponseEntity<TaskDto> updateTask(@PathVariable String token, @RequestBody @Valid Task updatedTask) throws Exception {
	    return new ResponseEntity<>(taskService.updateTask(token, updatedTask), HttpStatus.OK);
	}

	/**
	 * Assign a task to another user.
	 */
	@PutMapping("/tasks/{token}/{taskId}/{userId}")
	public ResponseEntity<TaskDto> assignTaskToAnotherUser(@PathVariable String token, @PathVariable Long taskId, @PathVariable Long userId) throws Exception {
	    return new ResponseEntity<>(taskService.assignTaskToAnotherUser(token, taskId, userId), HttpStatus.OK);
	}

	/**
	 * Mark a task as complete.
	 */
	@PutMapping("/tasks/complete/{token}/{taskId}")
	public ResponseEntity<TaskDto> markTaskComplete(@PathVariable String token, @PathVariable Long taskId) throws Exception {
	    return new ResponseEntity<>(taskService.markTaskComplete(token, taskId), HttpStatus.OK);
	}

	/**
	 * Delete a task.
	 */
	@DeleteMapping("/tasks/{token}/{taskId}")
	public ResponseEntity<Task> deleteTask(@PathVariable String token, @PathVariable Long taskId) throws Exception {
	    return new ResponseEntity<>(taskService.deleteTask(token, taskId), HttpStatus.OK);
	}

	/**
	 * Get task details by ID.
	 */
	@GetMapping("/tasks/{taskId}")
	public ResponseEntity<TaskDto> getTask(@PathVariable Long taskId) throws Exception {
	    return new ResponseEntity<>(taskService.getTaskById(taskId), HttpStatus.OK);
	}

	/**
	 * Search tasks by title.
	 */
	@GetMapping("/search/title/{token}/{title}")
	public ResponseEntity<List<TaskDto>> searchTaskByTitle(@PathVariable String token, @PathVariable String title) throws Exception {
	    return new ResponseEntity<>(taskService.searchTaskByTitle(token, title), HttpStatus.OK);
	}

	/**
	 * Search tasks by description.
	 */
	@GetMapping("/search/desc/{token}/{desc}")
	public ResponseEntity<List<TaskDto>> searchTaskByDescription(@PathVariable String token, @PathVariable String desc) throws Exception {
	    return new ResponseEntity<>(taskService.searchTaskByDescription(token, desc), HttpStatus.OK);
	}

	/**
	 * Search tasks assigned to a user.
	 */
	@GetMapping("/search/user/{userId}")
	public ResponseEntity<List<TaskDto>> searchTaskOfUser(@PathVariable Long userId) throws Exception {
	    return new ResponseEntity<>(taskService.searchTaskOfUser(userId), HttpStatus.OK);
	}

	/**
	 * Filter tasks by completion status.
	 */
	@GetMapping("/filter/completionstatus/{token}/{completedStatus}")
	public ResponseEntity<List<TaskDto>> filterTaskByCompletionStatus(@PathVariable String token, @PathVariable Boolean completedStatus) throws Exception {
	    return new ResponseEntity<>(taskService.filterTaskByCompletionStatus(token, completedStatus), HttpStatus.OK);
	}

	/**
	 * Filter tasks by due date.
	 */
	@GetMapping("/filter/duedate/{token}/{dueDate}")
	public ResponseEntity<List<TaskDto>> filterTaskByDueDate(@PathVariable String token, @PathVariable LocalDate dueDate) throws Exception {
	    return new ResponseEntity<>(taskService.filterTaskByDueDate(token, dueDate), HttpStatus.OK);
	}

	/**
	 * Filter tasks by completion status and due date.
	 */
	@GetMapping("/filter/{token}/{completedStatus}/{dueDate}")
	public ResponseEntity<List<TaskDto>> filterTaskByCompletionStatusAndDueDate(
	        @PathVariable String token, @PathVariable Boolean completedStatus, @PathVariable LocalDate dueDate) throws Exception {
	    return new ResponseEntity<>(taskService.filterTaskByCompletionStatusAndDueDate(token, completedStatus, dueDate), HttpStatus.OK);
	}

	

}
