package hh.homework.taskManager.controllers;

import hh.homework.taskManager.entities.Task;
import hh.homework.taskManager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class TaskController {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    @GetMapping("/home")
    public String findAll(Model model){
        var tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "home/home.html";
    }
    @PostMapping("/home")
    public String addTask(Model model, @RequestParam String taskName, @RequestParam(required = false) LocalDate deadline){
        Task task = new Task(taskName, LocalDate.now(), deadline != null ? deadline : null, "Started");
        taskRepository.save(task);
        var tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "home/home.html";
    }
    @DeleteMapping("/home")
    public String deleteTask(Model model, @RequestParam("id") String stringId){
        UUID id = UUID.fromString(stringId);
        taskRepository.deleteById(id);
        var tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "home/home.html";
    }
    @GetMapping("/edit")
    public String editTask(Model model, @RequestParam(name = "id") String stringId){
        UUID id = UUID.fromString(stringId);
        model.addAttribute("task", taskRepository.getReferenceById(id));
        return "home/edit.html";
    }
    @PatchMapping("/edit")
    public String updateTask(@ModelAttribute("task") Task task, Model model){
        taskRepository.save(task);
        var tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "redirect:home";
    }
}
