package com.muster_dekho.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muster_dekho.dto.TaskDto;
import com.muster_dekho.exception.TaskException;
import com.muster_dekho.exception.TaskFoundException;
import com.muster_dekho.exception.UserFoundException;
import com.muster_dekho.exception.UserLoginException;
import com.muster_dekho.model.CurrentLoginSession;
import com.muster_dekho.model.Task;
import com.muster_dekho.model.User;
import com.muster_dekho.repository.CurrentUserSessionRepo;
import com.muster_dekho.repository.TaskRepository;
import com.muster_dekho.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CurrentUserSessionRepo currentUserRepo;

	@Override
	public TaskDto createTask(String token, Task task) throws  Exception {
		
		List<CurrentLoginSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new Exception();
		}
		
		CurrentLoginSession loggedInUser = validation.get(0);
		
		User user = userRepo.findById(loggedInUser.getUserId())
				.orElseThrow(() -> new UserFoundException(loggedInUser.getUserId()));
		
		task.setAssignedUser(user);
		
		Task saved = taskRepo.save(task);
		
		TaskDto dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public TaskDto updateTask(String token, Task updatedTask) throws Exception {
		
		List<CurrentLoginSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new Exception();
		}
		
		CurrentLoginSession loggedInUser = validation.get(0);
		
		Task savedTask = taskRepo.findById(updatedTask.getId())
				.orElseThrow(() -> new TaskFoundException(updatedTask.getId()));
		
		if(savedTask.getAssignedUser().getId()+1 != loggedInUser.getUserId()+1)
			throw new TaskException("This is not your task! you canno't update it");
		
		if(updatedTask.getDescription()!=null)
			savedTask.setDescription(updatedTask.getDescription());
		
		if(updatedTask.getTitle()!=null)
			savedTask.setTitle(updatedTask.getTitle());
		
		if(updatedTask.getDueDate()!=null)
			savedTask.setDueDate(updatedTask.getDueDate());
		
		Task saved = taskRepo.save(savedTask);
		
		TaskDto dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public TaskDto assignTaskToAnotherUser(String token, Long taskId, Long userId) 
			throws Exception {

		List<CurrentLoginSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserLoginException();
		}
		
		CurrentLoginSession loggedInUser = validation.get(0);
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskFoundException(taskId));
		
		if(task.getAssignedUser().getId()+1 != loggedInUser.getUserId()+1)
			throw new TaskException("This is not your task! you canno't update it");
		
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserFoundException(userId));
		
		task.setAssignedUser(user);
		
		Task saved = taskRepo.save(task);
		
		TaskDto dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public TaskDto markTaskComplete(String token, Long taskId) throws Exception {
		
		List<CurrentLoginSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserLoginException();
		}
		
		CurrentLoginSession loggedInUser = validation.get(0);
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskFoundException(taskId));
		
		if(task.getAssignedUser().getId()+1 != loggedInUser.getUserId()+1)
			throw new TaskException("This is not your task! you canno't update it");
		
		if(task.isCompleted())
			task.setCompleted(false);
		else 
			task.setCompleted(true);
		
		Task saved = taskRepo.save(task);
		
		TaskDto dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public Task deleteTask(String token, Long taskId) throws Exception{

		List<CurrentLoginSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserLoginException();
		}
		
		CurrentLoginSession loggedInUser = validation.get(0);
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskFoundException(taskId));
		
		if(task.getAssignedUser().getId()+1 != loggedInUser.getUserId()+1)
			throw new TaskException("This is not your task! you canno't update it");
		
		
		taskRepo.delete(task);
		
		return task;
	}

	@Override
	public TaskDto getTaskById(Long taskId) throws Exception {
		
		Task saved = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskFoundException(taskId));
		
		TaskDto dto = convertToTaskDTO(saved);
		
		return dto;
		
	}
	
	private TaskDto convertToTaskDTO(Task task) {
		
		TaskDto dto = new TaskDto();
		
		dto.setCompleted(task.isCompleted());
		dto.setDescription(task.getDescription());
		dto.setDueDate(task.getDueDate());
		dto.setId(task.getId());
		dto.setTitle(task.getTitle());
		dto.setName(task.getAssignedUser().getName());
		dto.setUserId(task.getAssignedUser().getId());
		
		return dto;
		
	}
	@Override
	public List<TaskDto> searchTaskByTitle(String token, String title) throws Exception {
		
		List<CurrentLoginSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserLoginException();
		}
		
		CurrentLoginSession loggedInUser = validation.get(0);
		
		List<Task> tasks = taskRepo.findByAssignedUserIdAndTitleContainingIgnoreCase(loggedInUser.getUserId(), title);
		
		if(tasks.isEmpty())
			throw new TaskFoundException();
		
		List<TaskDto> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}

	@Override
	public List<TaskDto> searchTaskByDescription(String token, String description) throws Exception {
		
List<CurrentLoginSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserLoginException();
		}
		
		CurrentLoginSession loggedInUser = validation.get(0);
		
		List<Task> tasks = taskRepo.findByAssignedUserIdAndDescriptionContainingIgnoreCase(loggedInUser.getUserId(), description);
		
		if(tasks.isEmpty())
			throw new TaskFoundException();
		
		List<TaskDto> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}

	@Override
	public List<TaskDto> searchTaskOfUser(Long userId) throws Exception{

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserFoundException(userId));
		
		if(user.getTasks().isEmpty())
			throw new TaskFoundException();
		
		List<TaskDto> dtos = convertToTaskDTO(user.getTasks());
		
		return dtos;
		
	}

	@Override
	public List<TaskDto> filterTaskByCompletionStatus(String token, Boolean completedStatus) throws Exception {
		
		List<CurrentLoginSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserLoginException();
		}
		
		CurrentLoginSession loggedInUser = validation.get(0);
		
		User user = userRepo.findById(loggedInUser.getUserId())
				.orElseThrow(() -> new UserFoundException(loggedInUser.getUserId()));
		
		
		List<Task> tasks = user.getTasks();
		
		tasks = tasks.stream()
				.filter( task -> 
				task.isCompleted() == completedStatus)
				.collect(Collectors.toList());
		
		if(tasks.isEmpty())
			throw new TaskFoundException();
		
		List<TaskDto> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}

	@Override
	public List<TaskDto> filterTaskByDueDate(String token, LocalDate dueDate) throws Exception {
		
		List<CurrentLoginSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserLoginException();
		}
		
		CurrentLoginSession loggedInUser = validation.get(0);
		
		User user = userRepo.findById(loggedInUser.getUserId())
				.orElseThrow(() -> new UserFoundException(loggedInUser.getUserId()));
		
		
		List<Task> tasks = user.getTasks();
		
		tasks = tasks.stream()
				.filter( task -> 
				task.getDueDate().equals(dueDate))
				.collect(Collectors.toList());
		
		if(tasks.isEmpty())
			throw new TaskFoundException();
		
		List<TaskDto> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}

	@Override
	public List<TaskDto> filterTaskByCompletionStatusAndDueDate(String token, Boolean completedStatus, LocalDate dueDate) throws Exception {

		List<Task> tasks = taskRepo.findAll();
		
		tasks = tasks.stream()
				.filter( task -> 
				task.getDueDate().equals(dueDate)&&
				task.getDueDate().equals(dueDate))
				.collect(Collectors.toList());
		
		if(tasks.isEmpty())
			throw new TaskFoundException();
		
		List<TaskDto> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}
	
	
	private List<TaskDto> convertToTaskDTO(List<Task> tasks) {
		
		List<TaskDto> dtos = new ArrayList<>();
		
		tasks.forEach( task -> {
			
			TaskDto dto = new TaskDto();
			
			dto.setCompleted(task.isCompleted());	
			dto.setDescription(task.getDescription());
			dto.setDueDate(task.getDueDate());
			dto.setId(task.getId());
			dto.setTitle(task.getTitle());
			dto.setName(task.getAssignedUser().getName());
			dto.setUserId(task.getAssignedUser().getId());
			dtos.add(dto);
			
		});
		
		return dtos;
		
	}

}
