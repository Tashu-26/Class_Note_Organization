package com.noteorg.facade;

import com.noteorg.model.User;
import com.noteorg.repository.TaskRepository;
import com.noteorg.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class DashboardFacade {

    @Autowired
    private NoteService noteService;

    @Autowired
    private TaskRepository taskRepository;

    public void prepareDashboard(Model model, User user) {

        model.addAttribute("stats",
                noteService.getDashboardStats(user));

        model.addAttribute("recentNotes",
                noteService.getRecentNotes(user));

        model.addAttribute("subjects",
                noteService.getSubjects(user));

        model.addAttribute("tasks",
                taskRepository.findByUserAndCompletedFalseOrderByDueDateAsc(user));

        model.addAttribute("completedTasks",
                taskRepository.countByUserAndCompletedTrue(user));

        model.addAttribute("pendingTasks",
                taskRepository.countByUserAndCompletedFalse(user));
    }
}
