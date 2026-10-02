package com.example.tasktracker.dao;

import com.example.tasktracker.model.Task;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task add(Task task);

    Task update(Task task);
    Task setCompleted(long id, boolean isCompleted);


    Task deleteById(long id);


    Optional<Task> findById(long id);
    List<Task> findAll();
    List<Task> findByParams(@Nullable Boolean completed, @Nullable Integer limit);

    long getTaskCount();

}
