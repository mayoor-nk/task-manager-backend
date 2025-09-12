package com.example.task.controller;

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

import com.example.task.dto.TaskRequest;
import com.example.task.model.Task;
import com.example.task.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	
	@PostMapping 
	public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest req) { 
		Task task=new Task(
		        req.getTitle(),
		        req.getDescription(),
		        req.getDueDate(),
		        req.getStatus() == null ? Task.Status.TODO : req.getStatus()
		    );
		Task saved = taskService.createTask(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	 
	

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTasks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.getTaskById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequest req) {
		Task updatedTask = new Task(
				req.getTitle(),
		        req.getDescription(),
		        req.getDueDate(),
		        req.getStatus() == null ? Task.Status.TODO : req.getStatus()
				);
		Task saved=taskService.updateTask(id, updatedTask);
		return ResponseEntity.ok(saved);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
}
