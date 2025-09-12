package com.example.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.exception.ResourceNotFoundException;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public Task createTask(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Task getTaskById(Long id) {
		return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
	}

	
	public Task updateTask(Long id, Task updatedTask) {
	  
		Task task = getTaskById(id); task.setTitle(updatedTask.getTitle());
		task.setDescription(updatedTask.getDescription());
		task.setDueDate(updatedTask.getDueDate());
		task.setStatus(updatedTask.getStatus()); return taskRepository.save(task);
	  
	}
	 

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
}
