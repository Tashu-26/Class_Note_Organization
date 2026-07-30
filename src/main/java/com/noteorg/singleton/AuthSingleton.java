package com.noteorg.singleton;

import org.springframework.stereotype.Controller;

@Controller
public class AuthSingleton {

    public String login() {
        return "login";
    }
}