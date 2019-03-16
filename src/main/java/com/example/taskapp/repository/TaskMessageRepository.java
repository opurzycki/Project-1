package com.example.taskapp.repository;

import com.example.taskapp.model.TaskMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskMessageRepository extends JpaRepository<TaskMessage, Integer> {
}
