package com.example.task.dto;

import java.time.LocalDate;

import com.example.task.model.Task;
import com.example.task.model.Task.Status;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class TaskRequest {
	
	@NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    private String description;

    @FutureOrPresent(message = "dueDate must be today or in the future")
    private LocalDate dueDate;

    // optional on create — if null we'll default to TODO in controller/service
    private Task.Status status;

    public TaskRequest() {}

    public TaskRequest(String title, String description, LocalDate dueDate, Task.Status status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Task.Status getStatus() {
		return status;
	}

	public void setStatus(Task.Status status) {
		this.status = status;
	}
    
    
    
    
}
