package web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.Priority;
import project.Task;
import project.TaskRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "HIGH") Priority priority, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("tasks", taskRepository.findByPriority(priority));
        model.addAttribute("selectedPriority", priority);
        return "tasks";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable("id") long id, Model model) {
        model.addAttribute("task", taskRepository.findById(id));
        return "task_details";
    }

    @GetMapping("/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task_create";
    }

    @PostMapping("/new")
    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tasks", taskRepository.findAll());
            return "task_create";
        }
        taskRepository.add(task);
        return "redirect:/tasks";
    }
}