package com.noteorg.controller;

import com.noteorg.model.Subject;
import com.noteorg.model.User;
import com.noteorg.repository.SubjectRepository;
import com.noteorg.repository.UserRepository;
import com.noteorg.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteService noteService;

    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        User user = getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("subjects", subjectRepository.findByUser(user));
        }
        return "subjects";
    }

    @PostMapping("/subjects/save")
    public String saveSubject(Subject subject) {
        User user = getCurrentUser();
        if (user != null) {
            subject.setUser(user);
            subjectRepository.save(subject);
        }
        return "redirect:/notes";
    }

    @GetMapping("/subjects/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        User user = getCurrentUser();
        subjectRepository.findById(id).ifPresent(subject -> {
            if (subject.getUser().getId().equals(user.getId())) {
                subjectRepository.delete(subject);
            }
        });
        return "redirect:/subjects";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName()).orElse(null);
    }
}