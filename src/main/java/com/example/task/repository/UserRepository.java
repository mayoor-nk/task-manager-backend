package com.example.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByUsername(String username);
}
