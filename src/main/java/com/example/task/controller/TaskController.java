package com.example.task.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping
    public List<Task> getTasks(Principal principal) {
        return taskService.getTasks(principal.getName());
    }

    @PostMapping
    public Task createTask(@RequestBody TaskRequest request, Principal principal) {
        return taskService.createTask(request, principal.getName());
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskRequest request, Principal principal) {
        return taskService.updateTask(id, request, principal.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id, Principal principal) {
        taskService.deleteTask(id, principal.getName());
    }

    // Filtering endpoints
    @GetMapping("/status/{status}")
    public List<Task> filterByStatus(@PathVariable String status, Principal principal) {
        return taskService.filterByStatus(principal.getName(), status);
    }

    @GetMapping("/priority/{priority}")
    public List<Task> filterByPriority(@PathVariable String priority, Principal principal) {
        return taskService.filterByPriority(principal.getName(), priority);
    }

    @GetMapping("/dueBefore/{date}")
    public List<Task> filterByDueDate(@PathVariable String date, Principal principal) {
        return taskService.filterByDueDate(principal.getName(), LocalDate.parse(date));
    }
}
