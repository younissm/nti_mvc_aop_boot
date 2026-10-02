package com.example.tasktracker.controller;

import com.example.tasktracker.dao.TaskRepository;
import com.example.tasktracker.dto.CreateUpdateTaskRequest;
import com.example.tasktracker.exception.MaxTasksReachedException;
import com.example.tasktracker.exception.TaskNotFoundException;
import com.example.tasktracker.model.Task;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("api/tasks")
public class TaskController {

    private final int defaultPageSize;
    private final int maxTasks;
    private final TaskRepository taskRepository;
    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    public TaskController(@Value("${tasktracker.max-tasks}") int maxTasks,
                          @Value("${tasktracker.default-page-size}") int defaultPageSize, TaskRepository taskRepository) {
        this.defaultPageSize = defaultPageSize;
        this.maxTasks = maxTasks;
        this.taskRepository = taskRepository;
    }

    @PostMapping
    private ResponseEntity<Task> createTask(@Valid @RequestBody CreateUpdateTaskRequest taskDto) {
        if (taskRepository.getTaskCount() >= maxTasks) {
            throw new MaxTasksReachedException();
        }

        Task task = new Task(taskDto.title(), taskDto.description(), taskDto.completed(), taskDto.dueDate());

        Task createdTask = taskRepository.add(task);
        return ResponseEntity.created(URI.create("api/tasks/"+createdTask.getId())).body(createdTask);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Task> putTask(@PathVariable long id, @Valid @RequestBody CreateUpdateTaskRequest taskDto) {
        Task task = new Task(taskDto.title(), taskDto.description(), taskDto.completed(), taskDto.dueDate());
        task.setId(id);

        Task updatedTask = taskRepository.update(task);

        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/{id}/completed")
    private ResponseEntity<Task> markTaskCompleted(@PathVariable long id) {

        Task task = taskRepository.setCompleted(id, true);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Task> deleteTask(@PathVariable long id) {
        Task deletedTask = taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    @ResponseBody
    private List<Task> listTasks(@RequestParam(value = "completed", required = false) String completed, @RequestParam(value = "limit", required = false) Integer pageSize) {
        Boolean isCompleted = null;
        if (completed != null ) {
            if (completed.equals("true")) {
                isCompleted = true;
            } else if (completed.equals("false")) {
                isCompleted = false;
            } else {
                log.warn("Value passed in 'completed' parameter is invalid, fetching all tasks");
                isCompleted = null;
            }
        }

        int currentLimit = defaultPageSize;
        if (pageSize != null) {
            currentLimit = pageSize;
        } else {
            log.warn("pageSize parameter is not set, limit is set to default. (" + defaultPageSize+ ")");
        }

        return taskRepository.findByParams(isCompleted, currentLimit);
    }


    @GetMapping("/{id}")
    private ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Couldn't find task with id " + id)));
    }
}
