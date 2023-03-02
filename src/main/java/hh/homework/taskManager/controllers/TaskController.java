package hh.homework.taskManager.controllers;

import hh.homework.taskManager.entities.Task;
import hh.homework.taskManager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class TaskController {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    @GetMapping("/tasks")
    public String findAll(Model model){
        var tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks/tasks";
    }
    @PostMapping("/tasks")
    public String addTask(Model model, @RequestParam String taskName, @RequestParam(required = false) LocalDate deadline){
        Task task = new Task(taskName, deadline);
        taskRepository.save(task);
        var tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks/tasks";
    }
    @DeleteMapping("/tasks")
    public String deleteTask(Model model, @RequestParam("id") String stringId){
        Integer id = Integer.parseInt(stringId);
        if(taskRepository.existsById(id))
            taskRepository.deleteById(id);
        var tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks/tasks";
    }
    @GetMapping("/edit")
    public String editTask(Model model, @RequestParam(name = "id") String stringId){
        Integer id = Integer.parseInt(stringId);
        if (taskRepository.existsById(id)) {
            Optional<Task> taskToBeFoundOpt = taskRepository.findById(id);
            Task taskToBeFound = taskToBeFoundOpt.get();
            model.addAttribute("task", taskToBeFound);
            return "tasks/edit";
        } else
            return "tasks/error";
    }
    @PatchMapping("/edit")
    public String updateTask(@ModelAttribute("task") Task task, Model model){
        if(taskRepository.existsById(task.getId())) {
            taskRepository.save(task);
            var tasks = taskRepository.findAll();
            //tasks.sort(Comparator.comparing(Task::getTaskName));
            model.addAttribute("tasks", tasks);
            return "redirect:tasks";
        } else
            return "tasks/error";
    }
}
