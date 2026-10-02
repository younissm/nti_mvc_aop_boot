package com.example.tasktracker.dao;

import com.example.tasktracker.exception.TaskNotFoundException;
import com.example.tasktracker.model.Task;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("dev")
public class InMemoryTaskRepository implements TaskRepository{
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong nextAvailableId = new AtomicLong(1);
    private final Logger log = LoggerFactory.getLogger(JpaTaskRepository.class);


    public InMemoryTaskRepository() {
        log.debug("Dev profile is set, initializing InMemoryTaskRepository");
    }

    @Override
    public Task add(Task task) {
        long taskId = nextAvailableId.getAndIncrement();
        task.setId(taskId);
        tasks.put(taskId, task);
        return task;
    }

    @Override
    public Task update(Task task) {
        if (!tasks.containsKey(task.getId())) {
            throw new TaskNotFoundException("Couldn't find task with id " + task.getId());
        }

        tasks.put(task.getId(), task);

        return task;
    }

    @Override
    public Task setCompleted(long id, boolean isCompleted) {
        Task task = findById(id).orElseThrow(() -> new TaskNotFoundException("Couldn't find task with id " + id));
        task.setCompleted(isCompleted);
        tasks.put(id, task);
        return task;
    }

    @Override
    public Task deleteById(long id) {
        if (!tasks.containsKey(id)) {
            throw new TaskNotFoundException("Couldn't find task with id " + id);
        }
        Task task = tasks.get(id);
        tasks.remove(id);
        return task;
    }

    @Override
    public Optional<Task> findById(long id) {
        if (!tasks.containsKey(id)) {
            throw new TaskNotFoundException("Couldn't find task with id " + id);
        }
        return Optional.of(tasks.get(id));
    }

    @Override
    public List<Task> findAll() {
        return List.copyOf(tasks.values());
    }

    @Override
    public List<Task> findByParams(@Nullable Boolean completed, @Nullable Integer limit) {
        List<Task> resultTasks = List.copyOf(tasks.values());
        if (completed != null ) {
                resultTasks = resultTasks.stream().filter((task) -> completed.equals(task.isCompleted()))
                        .toList();
        }

        if (limit != null) {
            return resultTasks.stream().limit(limit).toList();
        }

        return resultTasks;
    }

    @Override
    public long getTaskCount() {
        return tasks.size();
    }


}
