package project;

import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public TaskRepository() {
    }

    public Task add(Task task) {
        task.setId(nextId.incrementAndGet());
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> findAll() {
        return tasks.values().stream()
                .sorted(Comparator.comparingLong(Task::getId))
                .toList();
    }

    public List<Task> findByPriority(Priority priority) {
        return findAll().stream()
                .filter(t -> t.getPriority() == priority)
                .toList();
    }

    public Task findById(long id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new TaskNotFoundException("Task " + id + " was not found");
        }
        return task;
    }
}