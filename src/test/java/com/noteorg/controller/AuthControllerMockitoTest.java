package com.noteorg.controller;

import com.noteorg.model.User;
import com.noteorg.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerMockitoTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Model model;

    @InjectMocks
    private AuthController authController;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Monisha");
        user.setEmail("monisha@test.com");
        user.setPassword("12345");
    }
    
    @Test
    void testLogin() {
        String view = authController.login();
        assertEquals("login", view);
    }

    @Test
    void testSignup() {
        String view = authController.signup(model);

        assertEquals("signup", view);
        verify(model).addAttribute(eq("user"), any(User.class));
    }

    @Test
    void testRegister() {

        when(passwordEncoder.encode("12345")).thenReturn("encodedPassword");

        String view = authController.register(user);

        assertEquals("redirect:/login", view);
        assertEquals("encodedPassword", user.getPassword());

        verify(userRepository).save(user);
    }
}