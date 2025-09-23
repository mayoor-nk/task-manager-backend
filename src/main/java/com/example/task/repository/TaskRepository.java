package com.example.task.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.task.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
	
	List<Task> findByUserId(String userId);
    List<Task> findByUserIdAndStatus(String userId, String status);
    List<Task> findByUserIdAndPriority(String userId, String priority);
    List<Task> findByUserIdAndDueDateBefore(String userId, LocalDate dueDate);
}
