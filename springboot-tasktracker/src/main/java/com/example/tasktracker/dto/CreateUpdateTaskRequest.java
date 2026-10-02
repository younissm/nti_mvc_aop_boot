package com.example.tasktracker.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateUpdateTaskRequest(
     @NotBlank @Size(min=3, max=30) String title,
     @Size(min=3, max=300) String description,
     boolean completed,
     LocalDate dueDate
){}
