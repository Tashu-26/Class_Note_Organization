package com.noteorg.controller;

import com.noteorg.model.Task;
import com.noteorg.model.User;
import com.noteorg.repository.TaskRepository;
import com.noteorg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/tasks/save")
    public String saveTask(
            @RequestParam String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
            @RequestParam String priority,
            @RequestParam String color) {
        User user = getCurrentUser();
        if (user != null) {
            Task task = new Task();
            task.setTitle(title);
            task.setDescription(description);
            task.setDueDate(dueDate);
            task.setPriority(priority);
            task.setColor(color);
            task.setUser(user);
            taskRepository.save(task);
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/tasks/complete/{id}")
    public String completeTask(@PathVariable Long id) {
        User user = getCurrentUser();
        taskRepository.findById(id).ifPresent(task -> {
            if (task.getUser().getId().equals(user.getId())) {
                task.setCompleted(!task.isCompleted());
                taskRepository.save(task);
            }
        });
        return "redirect:/dashboard";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        User user = getCurrentUser();
        taskRepository.findById(id).ifPresent(task -> {
            if (task.getUser().getId().equals(user.getId())) {
                taskRepository.delete(task);
            }
        });
        return "redirect:/dashboard";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName()).orElse(null);
    }
}