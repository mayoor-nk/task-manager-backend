package com.example.task.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.dto.TaskRequest;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	public List<Task> getTasks(String userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task createTask(TaskRequest request, String userId) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus() != null ? request.getStatus() : "PENDING");
        task.setPriority(request.getPriority() != null ? request.getPriority() : "MEDIUM");
        task.setDueDate(request.getDueDate());
        task.setUserId(userId);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskRequest request, String userId) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to update this task");
        }
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus() != null ? request.getStatus() : task.getStatus());
        task.setPriority(request.getPriority() != null ? request.getPriority() : task.getPriority());
        task.setDueDate(request.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id, String userId) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to delete this task");
        }
        taskRepository.delete(task);
    }

    public List<Task> filterByStatus(String userId, String status) {
        return taskRepository.findByUserIdAndStatus(userId, status);
    }

    public List<Task> filterByPriority(String userId, String priority) {
        return taskRepository.findByUserIdAndPriority(userId, priority);
    }

    public List<Task> filterByDueDate(String userId, LocalDate dueDate) {
        return taskRepository.findByUserIdAndDueDateBefore(userId, dueDate);
    }
}
