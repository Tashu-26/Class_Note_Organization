package com.noteorg.controller;

import com.noteorg.model.Note;
import com.noteorg.model.User;
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
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/notes")
    public String listNotes(Model model) {
        User user = getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("notes", noteService.getRecentNotes(user));
            model.addAttribute("subjects", noteService.getSubjects(user));
            model.addAttribute("note", new Note()); // For the create form
        }
        return "notes";
    }

    @PostMapping("/notes/save")
    public String saveNote(Note note) {
        User user = getCurrentUser();
        if (user != null) {
            noteService.saveNote(note, user);
        }
        return "redirect:/notes";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        User user = getCurrentUser();
        if (user != null) {
            noteService.deleteNote(id, user);
        }
        return "redirect:/notes";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName()).orElse(null);
    }
}
