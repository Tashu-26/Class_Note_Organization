package com.noteorg.controller;

import com.noteorg.model.User;
import com.noteorg.repository.TaskRepository;
import com.noteorg.repository.UserRepository;
import com.noteorg.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName()).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("stats", noteService.getDashboardStats(user));
            model.addAttribute("recentNotes", noteService.getRecentNotes(user));
            model.addAttribute("subjects", noteService.getSubjects(user));
            model.addAttribute("tasks", taskRepository.findByUserAndCompletedFalseOrderByDueDateAsc(user));
            model.addAttribute("completedTasks", taskRepository.countByUserAndCompletedTrue(user));
            model.addAttribute("pendingTasks", taskRepository.countByUserAndCompletedFalse(user));
        }
        return "dashboard";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }
}