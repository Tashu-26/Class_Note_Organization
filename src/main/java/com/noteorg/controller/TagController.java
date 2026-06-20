package com.noteorg.controller;

import com.noteorg.model.Tag;
import com.noteorg.model.User;
import com.noteorg.repository.TagRepository;
import com.noteorg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/tags")
    public String listTags(Model model) {
        User user = getCurrentUser();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("tags", tagRepository.findByUser(user));
        }
        return "tags";
    }

    @PostMapping("/tags/save")
    public String saveTag(Tag tag) {
        User user = getCurrentUser();
        if (user != null) {
            tag.setUser(user);
            tagRepository.save(tag);
        }
        return "redirect:/tags";
    }

    @GetMapping("/tags/delete/{id}")
    public String deleteTag(@PathVariable Long id) {
        User user = getCurrentUser();
        tagRepository.findById(id).ifPresent(tag -> {
            if (tag.getUser().getId().equals(user.getId())) {
                tagRepository.delete(tag);
            }
        });
        return "redirect:/tags";
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName()).orElse(null);
    }
}