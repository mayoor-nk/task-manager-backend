package com.example.task.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Table
@Entity
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String title;

    private String description;
    
    private LocalDate dueDate;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        TODO,
        IN_PROGRESS,
        DONE
    }
    
    public Task() {}

    public Task(String title, String description, LocalDate dueDate, Status status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }
	
	  public Long getId() { return id; }
	  
	  
	  public String getTitle() { return title; }
	  
	  public void setTitle(String title) { this.title = title; }
	  
	  public String getDescription() { return description; }
	  
	  public void setDescription(String description) { this.description =
	  description; }
	  
	  public LocalDate getDueDate() { return dueDate; }
	  
	  public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
	  
	  public Status getStatus() { return status; }
	  
	  public void setStatus(Status status) { this.status = status; }
	 
    
}
