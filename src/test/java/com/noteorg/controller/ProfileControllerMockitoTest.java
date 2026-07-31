package com.noteorg.controller;

import com.noteorg.model.User;
import com.noteorg.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProfileControllerMockitoTest {

    @Mock
    UserRepository userRepository;

    @Mock
    Model model;

    @Mock
    Authentication authentication;

    @Mock
    SecurityContext securityContext;

    @InjectMocks
    ProfileController profileController;

    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setEmail("test@gmail.com");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("test@gmail.com");
        when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(user));
    }

    @Test
    void testProfile() {
        String result = profileController.profile(model);

        assertEquals("profile", result);
        verify(model).addAttribute("user", user);
    }

    @Test
    void testUpdateProfile() {
        profileController.updateProfile("Tasnia", "MU", "3rd", mock(org.springframework.web.servlet.mvc.support.RedirectAttributes.class));

        verify(userRepository).save(user);
    }
}
