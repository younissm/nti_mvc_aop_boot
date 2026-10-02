package com.example.tasktracker.exception;

public class MaxTasksReachedException extends RuntimeException {
    public MaxTasksReachedException() {
        super("Max no of tasks reached");
    }
}
